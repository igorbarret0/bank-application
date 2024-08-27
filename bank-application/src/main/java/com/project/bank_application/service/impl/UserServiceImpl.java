package com.project.bank_application.service.impl;

import com.project.bank_application.dtos.*;
import com.project.bank_application.entity.User;
import com.project.bank_application.repository.UserRepository;
import com.project.bank_application.service.EmailService;
import com.project.bank_application.service.UserService;
import com.project.bank_application.utils.AccountUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public BankResponse createAccount(UserRequest request) {

        var userExists = userRepository.existsByEmail(request.getEmail());
        if (userExists) {
            var bankResponse = new BankResponse();
            bankResponse.setResponseCode(AccountUtils.ACCOUNT_EXISTS_CODE);
            bankResponse.setResponseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE);
            bankResponse.setAccountInfo(null);

            return bankResponse;
        }

        User newUser = new User();

        newUser.setName(request.getName());
        newUser.setLastName(request.getLastName());
        newUser.setOtherName(request.getOtherName());
        newUser.setGender(request.getGender());
        newUser.setAddress(request.getAddress());
        newUser.setStateOfOrigin(request.getStateOfOrigin());
        newUser.setAccountNumber(AccountUtils.generateAccountNumber());
        newUser.setAccountBalance(BigDecimal.ZERO);
        newUser.setEmail(request.getEmail());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setAlternativePhone(request.getAlternativePhone());
        newUser.setStateOfOrigin(request.getStateOfOrigin());
        newUser.setStatus("ACTIVE");

        User savedUser = userRepository.save(newUser);

        // Send email alert

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(savedUser.getEmail());
        emailDetails.setMessageBody("Congrats! Your account has been successfully created");
        emailDetails.setSubject("Account Creation");

        emailService.sendEmail(emailDetails);

        return new BankResponse(
                AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE,
                AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE,
                new AccountInfo(
                        savedUser.getName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName(),
                        savedUser.getAccountBalance(),
                        savedUser.getAccountNumber()
                )
        );

    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request) {

        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!isAccountExist) {
            return new BankResponse(
                    AccountUtils.ACCOUNT_NOT_EXIST_CODE,
                    AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE,
                    null
            );
        }

        var user = userRepository.findByAccountNumber(request.getAccountNumber());

        return new BankResponse(
                AccountUtils.ACCOUNT_FOUND_CODE,
                AccountUtils.ACCOUNT_FOUND_SUCCESS,
                new AccountInfo(
                        user.getName() + " " + user.getLastName() + " " + user.getOtherName(),
                        user.getAccountBalance(),
                        request.getAccountNumber()
                )
        );

    }

    @Override
    public String nameEnquiry(EnquiryRequest request) {

        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }

        var user = userRepository.findByAccountNumber(request.getAccountNumber());
        return user.getName() + " " + user.getLastName() + " " + user.getLastName();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {

        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return new BankResponse(
                    AccountUtils.ACCOUNT_NOT_EXIST_CODE,
                    AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE,
                    null
            );
        }

        var userToCredit = userRepository.findByAccountNumber(request.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
        userRepository.save(userToCredit);

        return new BankResponse(
                AccountUtils.ACCOUNT_CREDIT_SUCCESS_CODE,
                AccountUtils.ACCOUNT_CREDIT_SUCCESS_MESSAGE,
                new AccountInfo(
                        userToCredit.getName() + " " + userToCredit.getLastName() + " " + userToCredit.getOtherName(),
                        userToCredit.getAccountBalance(),
                        userToCredit.getAccountNumber()
                )
        );
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {

        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return new BankResponse(
                    AccountUtils.ACCOUNT_NOT_EXIST_CODE,
                    AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE,
                    null
            );
        }

        var userToDebit = userRepository.findByAccountNumber(request.getAccountNumber());
        BigDecimal currentBalance = userToDebit.getAccountBalance();

        if (currentBalance.compareTo(request.getAmount()) < 0) {
            return new BankResponse(
                    AccountUtils.INSUFFICIENT_FUNDS_CODE,
                    AccountUtils.INSUFFICIENT_FUNDS_MESSAGE,
                    null
            );
        }

        BigDecimal newBalance = currentBalance.subtract(request.getAmount());
        userToDebit.setAccountBalance(newBalance);

        userRepository.save(userToDebit);

        return new BankResponse(
                    AccountUtils.ACCOUNT_DEBIT_SUCCESS_CODE,
                AccountUtils.ACCOUNT_DEBIT_SUCCESS_MESSAGE,
                new AccountInfo(
                        userToDebit.getName() + " " + userToDebit.getLastName() + " " + userToDebit.getOtherName(),
                        userToDebit.getAccountBalance(),
                        userToDebit.getAccountNumber()
                )
        );

    }

    @Override
    public BankResponse transfer(TransferRequest request) {

        boolean isAccountSenderExist = userRepository.existsByAccountNumber(request.getAccountNumberSender());
        boolean isAccountReceiverExist = userRepository.existsByAccountNumber(request.getAccountNumberReceiver());

        if (!isAccountSenderExist || !isAccountReceiverExist) {
            return new BankResponse(
                    AccountUtils.ACCOUNT_NOT_EXIST_CODE,
                    AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE,
                    null
            );
        }


        User accountSender = userRepository.findByAccountNumber(request.getAccountNumberSender());
        var debitAccountSender = new CreditDebitRequest(accountSender.getAccountNumber(), request.getAmount());
        if (request.getAmount().compareTo(accountSender.getAccountBalance()) > 0) {

            return new BankResponse(
                    AccountUtils.INSUFFICIENT_FUNDS_CODE,
                    AccountUtils.INSUFFICIENT_FUNDS_MESSAGE,
                    null
            );
        }
        debitAccount(debitAccountSender);

        EmailDetails debitAlert = new EmailDetails();
        debitAlert.setRecipient(accountSender.getEmail());
        debitAlert.setMessageBody("The amount of " + request.getAmount() + " has been deducted from your account");
        debitAlert.setSubject("DEBIT ALERT");
        emailService.sendEmail(debitAlert);

        User accountReceiver = userRepository.findByAccountNumber(request.getAccountNumberReceiver());
        var creditAccountReceiver = new CreditDebitRequest(accountReceiver.getAccountNumber(), request.getAmount());
        creditAccount(creditAccountReceiver);

        EmailDetails creditAlert = new EmailDetails();
        creditAlert.setRecipient(accountReceiver.getEmail());
        creditAlert.setMessageBody("The amount of " + request.getAmount() + " has been sent to your account");
        creditAlert.setSubject("CREDIT ALERT");
        emailService.sendEmail(creditAlert);


        return new BankResponse(
                AccountUtils.TRANSFER_SUCCESSFUL_CODE,
                AccountUtils.TRANSFER_SUCCESSFUL_MESSAGE,
                null
        );

    }
}

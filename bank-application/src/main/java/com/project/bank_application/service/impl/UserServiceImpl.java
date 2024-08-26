package com.project.bank_application.service.impl;

import com.project.bank_application.dtos.AccountInfo;
import com.project.bank_application.dtos.BankResponse;
import com.project.bank_application.dtos.UserRequest;
import com.project.bank_application.entity.User;
import com.project.bank_application.repository.UserRepository;
import com.project.bank_application.service.UserService;
import com.project.bank_application.utils.AccountUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
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
}
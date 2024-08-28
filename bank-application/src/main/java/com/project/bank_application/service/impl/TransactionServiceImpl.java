package com.project.bank_application.service.impl;

import com.project.bank_application.dtos.TransactionDto;
import com.project.bank_application.entity.Transaction;
import com.project.bank_application.repository.TransactionRepository;
import com.project.bank_application.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository  transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void saveTransaction(TransactionDto transactionDto) {

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAccountNumber(transactionDto.getAccountNumber());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setStatus("SUCCESS");

        transactionRepository.save(transaction);
        System.out.println("Transaction saved successfully");
    }
}

package com.project.bank_application.service;

import com.project.bank_application.dtos.TransactionDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    void saveTransaction(TransactionDto transactionDto);

}

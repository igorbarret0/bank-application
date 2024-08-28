package com.project.bank_application.controller;

import com.project.bank_application.entity.Transaction;
import com.project.bank_application.service.BankStatement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankStatement")
public class TransactionController {

    private BankStatement bankStatement;

    public TransactionController(BankStatement bankStatement) {
        this.bankStatement = bankStatement;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> generateBankStatement(@RequestParam String accountNumber,
                                                                   @RequestParam String startDate,
                                                                   @RequestParam String endDate) {

        var response = bankStatement.generateStatement(accountNumber, startDate, endDate);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

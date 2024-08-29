package com.project.bank_application.controller;

import com.itextpdf.text.DocumentException;
import com.project.bank_application.entity.Transaction;
import com.project.bank_application.service.BankStatementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bankStatement")
public class TransactionController {

    private BankStatementService bankStatementService;

    public TransactionController(BankStatementService bankStatementService) {
        this.bankStatementService = bankStatementService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> generateBankStatement(@RequestParam String accountNumber,
                                                                   @RequestParam String startDate,
                                                                   @RequestParam String endDate) throws DocumentException, FileNotFoundException {

        var response = bankStatementService.generateStatement(accountNumber, startDate, endDate);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

package com.project.bank_application.controller;

import com.project.bank_application.dtos.*;
import com.project.bank_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BankResponse> createAccount(@RequestBody UserRequest request) {

        var newUser = userService.createAccount(request);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/balanceEnquiry")
    public ResponseEntity<BankResponse> balanceEnquiry(@RequestBody EnquiryRequest request) {

        var response = userService.balanceEnquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/nameEnquiry")
    public ResponseEntity<String> nameEnquiry(@RequestBody EnquiryRequest request) {

        var response = userService.nameEnquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/credit")
    public ResponseEntity<BankResponse> creditAccount(@RequestBody CreditDebitRequest request) {

        var response = userService.creditAccount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/debit")
    public ResponseEntity<BankResponse> debitAccount(@RequestBody CreditDebitRequest request) {

        var response = userService.debitAccount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<BankResponse> transfer(@RequestBody TransferRequest request) {

        var response = userService.transfer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

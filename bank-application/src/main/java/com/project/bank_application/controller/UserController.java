package com.project.bank_application.controller;

import com.project.bank_application.dtos.*;
import com.project.bank_application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Tag(name = "User Account Management APIs")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(
            summary = "Create New User Account",
            description = "Creating a new user and assigning an account ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 created"
    )
    public ResponseEntity<BankResponse> createAccount(@RequestBody UserRequest request) {

        var newUser = userService.createAccount(request);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/balanceEnquiry")
    @Operation(
            summary = "Balance enquiry",
            description = "Given an account number, check how much the user has"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 0K"
    )
    public ResponseEntity<BankResponse> balanceEnquiry(@RequestBody EnquiryRequest request) {

        var response = userService.balanceEnquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/nameEnquiry")
    @Operation(
            summary = "Name Enquiry",
            description = "Given an account number, return the name owner of the account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    public ResponseEntity<String> nameEnquiry(@RequestBody EnquiryRequest request) {

        var response = userService.nameEnquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/credit")
    @Operation(
            summary = "Credit a account",
            description = "Given an account number, if account exists, is credited"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    public ResponseEntity<BankResponse> creditAccount(@RequestBody CreditDebitRequest request) {

        var response = userService.creditAccount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/debit")
    @Operation(
            summary = "Debit a account",
            description = "If the account exists and has sufficient funds, it will be debited."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    public ResponseEntity<BankResponse> debitAccount(@RequestBody CreditDebitRequest request) {

        var response = userService.debitAccount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    @Operation(
            summary = "Transfer Between Accounts",
            description = "This endpoint allows users to transfer funds between two bank accounts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    public ResponseEntity<BankResponse> transfer(@RequestBody TransferRequest request) {

        var response = userService.transfer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

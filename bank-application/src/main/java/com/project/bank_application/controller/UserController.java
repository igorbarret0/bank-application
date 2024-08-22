package com.project.bank_application.controller;

import com.project.bank_application.dtos.BankResponse;
import com.project.bank_application.dtos.UserRequest;
import com.project.bank_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

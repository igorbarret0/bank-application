package com.project.bank_application.service;

import com.project.bank_application.dtos.BankResponse;
import com.project.bank_application.dtos.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    BankResponse createAccount(UserRequest request);

}

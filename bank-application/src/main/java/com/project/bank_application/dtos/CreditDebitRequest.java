package com.project.bank_application.dtos;

import java.math.BigDecimal;

public class CreditDebitRequest {

    private String accountNumber;
    private BigDecimal amount;

    public CreditDebitRequest() {}

    public CreditDebitRequest(String accountNumberSender, BigDecimal amount) {
        this.accountNumber = accountNumberSender;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

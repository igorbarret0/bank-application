package com.project.bank_application.dtos;

import java.math.BigDecimal;

public class TransferRequest {

    private String accountNumberSender;
    private String accountNumberReceiver;
    private BigDecimal amount;

    public TransferRequest() {}

    public TransferRequest(String accountNumberSender, String accountNumberReceiver, BigDecimal amount) {
        this.accountNumberSender = accountNumberSender;
        this.accountNumberReceiver = accountNumberReceiver;
        this.amount = amount;
    }

    public String getAccountNumberSender() {
        return accountNumberSender;
    }

    public void setAccountNumberSender(String accountNumberSender) {
        this.accountNumberSender = accountNumberSender;
    }

    public String getAccountNumberReceiver() {
        return accountNumberReceiver;
    }

    public void setAccountNumberReceiver(String accountNumberReceiver) {
        this.accountNumberReceiver = accountNumberReceiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

package com.project.bank_application.dtos;

public class EnquiryRequest {

    private String accountNumber;

    public EnquiryRequest() {}

    public EnquiryRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

package com.project.bank_application.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account";

    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account has been created successfully";

    public static final String ACCOUNT_NOT_EXIST_CODE = "003";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE = "A account with this account number does`not exist";

    public static final String ACCOUNT_FOUND_CODE = "004";
    public static final String ACCOUNT_FOUND_SUCCESS = "User account found";

    public static final String ACCOUNT_CREDIT_SUCCESS_CODE = "005";
    public static final String ACCOUNT_CREDIT_SUCCESS_MESSAGE = "User account credited successfully";

    public static final String INSUFFICIENT_FUNDS_CODE = "006";
    public static final String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient quantity in your balance to withdraw";

    public static final String ACCOUNT_DEBIT_SUCCESS_CODE = "007";
    public static final String ACCOUNT_DEBIT_SUCCESS_MESSAGE = "User account debited successfully";

    public static String generateAccountNumber() {

        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

        // generate a random number between min and max
        int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

        // concatenate currentYear + random number
        String yearString = String.valueOf(currentYear);
        String randomNumberString = String.valueOf(randomNumber);

        StringBuilder accountNumber = new StringBuilder();

        accountNumber.append(yearString).append(randomNumberString);

        return accountNumber.toString();
    }


}

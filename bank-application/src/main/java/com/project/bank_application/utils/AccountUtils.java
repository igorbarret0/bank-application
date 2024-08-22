package com.project.bank_application.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account";

    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account has been created successfully";

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

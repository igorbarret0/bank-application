package com.project.bank_application.dtos;

public class UserRequest {

    private String name;

    private String lastName;

    private String otherName;

    private String gender;

    private String address;

    private String stateOfOrigin;

    private String email;

    private String phoneNumber;

    private String alternativePhone;


    public UserRequest() {}

    public UserRequest(String name, String lastName, String otherName, String gender, String address,
                       String stateOfOrigin, String email, String phoneNumber,
                       String alternativePhone) {
        this.name = name;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.address = address;
        this.stateOfOrigin = stateOfOrigin;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.alternativePhone = alternativePhone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

}

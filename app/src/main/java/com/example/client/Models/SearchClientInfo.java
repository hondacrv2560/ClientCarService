package com.example.client.Models;

public class SearchClientInfo {
    public String carGovNumber;
    public String phoneNumberClient;

    public SearchClientInfo(String carGovNumber, String phoneNumberClient) {
        this.carGovNumber = carGovNumber;
        this.phoneNumberClient = phoneNumberClient;
    }

    public SearchClientInfo() {
    }

    public String getCarGovNumber() {
        return carGovNumber;
    }

    public void setCarGovNumber(String carGovNumber) {
        this.carGovNumber = carGovNumber;
    }

    public String getPhoneNumberClient() {
        return phoneNumberClient;
    }

    public void setPhoneNumberClient(String phoneNumberClient) {
        this.phoneNumberClient = phoneNumberClient;
    }
}

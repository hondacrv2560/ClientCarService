package com.example.client.Models;

public class SearchClientInfo {
    public String carGovNumber;
    public String phoneNumberClient;
    public int startDayOfMonth;
    public int startTimeMonth;
    public int startTimeYear;

    public SearchClientInfo() {
    }

    public SearchClientInfo(String carGovNumber, String phoneNumberClient, int startDayOfMonth, int startTimeMonth, int startTimeYear) {
        this.carGovNumber = carGovNumber;
        this.phoneNumberClient = phoneNumberClient;
        this.startDayOfMonth = startDayOfMonth;
        this.startTimeMonth = startTimeMonth;
        this.startTimeYear = startTimeYear;
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

    public int getStartDayOfMonth() {
        return startDayOfMonth;
    }

    public void setStartDayOfMonth(int startDayOfMonth) {
        this.startDayOfMonth = startDayOfMonth;
    }

    public int getStartTimeMonth() {
        return startTimeMonth;
    }

    public void setStartTimeMonth(int startTimeMonth) {
        this.startTimeMonth = startTimeMonth;
    }

    public int getStartTimeYear() {
        return startTimeYear;
    }

    public void setStartTimeYear(int startTimeYear) {
        this.startTimeYear = startTimeYear;
    }
}

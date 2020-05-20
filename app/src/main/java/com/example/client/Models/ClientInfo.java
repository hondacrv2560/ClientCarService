package com.example.client.Models;

public class ClientInfo {
    public String UserId;
    public String govNumberCarNewClient;
    public String manufacturerCarNewClient;
    public String modelCarNewClient;
    public String nameSurnameNewClient;
    public String numPhoneNewClient;

    public ClientInfo() {
    }

    public ClientInfo(String userId, String govNumberCarNewClient, String manufacturerCarNewClient, String modelCarNewClient, String nameSurnameNewClient, String numPhoneNewClient) {
        UserId = userId;
        this.govNumberCarNewClient = govNumberCarNewClient;
        this.manufacturerCarNewClient = manufacturerCarNewClient;
        this.modelCarNewClient = modelCarNewClient;
        this.nameSurnameNewClient = nameSurnameNewClient;
        this.numPhoneNewClient = numPhoneNewClient;
    }

    public String getGovNumberCarNewClient() {
        return govNumberCarNewClient;
    }

    public void setGovNumberCarNewClient(String govNumberCarNewClient) {
        this.govNumberCarNewClient = govNumberCarNewClient;
    }

    public String getManufacturerCarNewClient() {
        return manufacturerCarNewClient;
    }

    public void setManufacturerCarNewClient(String manufacturerCarNewClient) {
        this.manufacturerCarNewClient = manufacturerCarNewClient;
    }

    public String getModelCarNewClient() {
        return modelCarNewClient;
    }

    public void setModelCarNewClient(String modelCarNewClient) {
        this.modelCarNewClient = modelCarNewClient;
    }

    public String getNameSurnameNewClient() {
        return nameSurnameNewClient;
    }

    public void setNameSurnameNewClient(String nameSurnameNewClient) {
        this.nameSurnameNewClient = nameSurnameNewClient;
    }

    public String getNumPhoneNewClient() {
        return numPhoneNewClient;
    }

    public void setNumPhoneNewClient(String numPhoneNewClient) {
        this.numPhoneNewClient = numPhoneNewClient;
    }
}

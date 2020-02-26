package com.example.client.Models;

public class ClientInfo {
    public String UserId;
    public String govNumberCarNewClient;
    public String manufacturerCarNewClient;
    public String modelCarNewClient;
    public String nameSurnameNewClient;
    public int numPhoneNewClient;

    public ClientInfo() {
    }

    public ClientInfo(String userId, String govNumberCarNewClient, String manufacturerCarNewClient, String modelCarNewClient, String nameSurnameNewClient, int numPhoneNewClient) {
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

    public int getNumPhoneNewClient() {
        return numPhoneNewClient;
    }

    public void setNumPhoneNewClient(int numPhoneNewClient) {
        this.numPhoneNewClient = numPhoneNewClient;
    }
}

package com.example.client.Models;

import java.io.Serializable;

public class Client implements Serializable {
    public String UserId;
    public String nameSurnameNewClient;
    public String numPhoneNewClient;
    public String govNumberCarNewClient;
    public String manufacturerCarNewClient;
    public String modelCarNewClient;
    public String setDataBirthday;

    public Client(){

    }

    public Client(String userId, String nameSurnameNewClient, String numPhoneNewClient, String govNumberCarNewClient, String manufacturerCarNewClient, String modelCarNewClient, String setDataBirthday) {
        UserId = userId;
        this.nameSurnameNewClient = nameSurnameNewClient;
        this.numPhoneNewClient = numPhoneNewClient;
        this.govNumberCarNewClient = govNumberCarNewClient;
        this.manufacturerCarNewClient = manufacturerCarNewClient;
        this.modelCarNewClient = modelCarNewClient;
        this.setDataBirthday = setDataBirthday;
    }

    public String getNameSurnameNewClient() {
        return nameSurnameNewClient;
    }

    public String getNumPhoneNewClient() {
        return numPhoneNewClient;
    }

    public String getGovNumberCarNewClient() {
        return govNumberCarNewClient;
    }

    public String getManufacturerCarNewClient() {
        return manufacturerCarNewClient;
    }

    public String getModelCarNewClient() {
        return modelCarNewClient;
    }

    public String getSetDataBirthday() {
        return setDataBirthday;
    }
}

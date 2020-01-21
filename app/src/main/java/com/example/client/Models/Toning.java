package com.example.client.Models;

public class Toning {
    public String idToning;
    public String titleToning;
    public String priceToning_sedan;
    public String priceToning_business;
    public String priceToning_premium;
    public String priceToning_SUV;
    public String priceToning_BigSUV;

    public Toning() {
    }

    public Toning(String idToning, String titleToning, String priceToning_sedan, String priceToning_business, String priceToning_premium, String priceToning_SUV, String priceToning_BigSUV) {
        this.idToning = idToning;
        this.titleToning = titleToning;
        this.priceToning_sedan = priceToning_sedan;
        this.priceToning_business = priceToning_business;
        this.priceToning_premium = priceToning_premium;
        this.priceToning_SUV = priceToning_SUV;
        this.priceToning_BigSUV = priceToning_BigSUV;
    }

    public String getIdToning() {
        return idToning;
    }

    public void setIdToning(String idToning) {
        this.idToning = idToning;
    }

    public String getTitleToning() {
        return titleToning;
    }

    public void setTitleToning(String titleToning) {
        this.titleToning = titleToning;
    }

    public String getPriceToning_sedan() {
        return priceToning_sedan;
    }

    public void setPriceToning_sedan(String priceToning_sedan) {
        this.priceToning_sedan = priceToning_sedan;
    }

    public String getPriceToning_business() {
        return priceToning_business;
    }

    public void setPriceToning_business(String priceToning_business) {
        this.priceToning_business = priceToning_business;
    }

    public String getPriceToning_premium() {
        return priceToning_premium;
    }

    public void setPriceToning_premium(String priceToning_premium) {
        this.priceToning_premium = priceToning_premium;
    }

    public String getPriceToning_SUV() {
        return priceToning_SUV;
    }

    public void setPriceToning_SUV(String priceToning_SUV) {
        this.priceToning_SUV = priceToning_SUV;
    }

    public String getPriceToning_BigSUV() {
        return priceToning_BigSUV;
    }

    public void setPriceToning_BigSUV(String priceToning_BigSUV) {
        this.priceToning_BigSUV = priceToning_BigSUV;
    }
}

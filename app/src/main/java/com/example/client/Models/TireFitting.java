package com.example.client.Models;

public class TireFitting {
    public String idTireFitting;
    public String titleTireFitting;
    public String priceTireFitting_sedan;
    public String priceTireFitting_business;
    public String priceTireFitting_premium;
    public String priceTireFitting_SUV;
    public String priceTireFitting_BigSUV;

    public TireFitting() {
    }

    public TireFitting(String idTireFitting, String titleTireFitting, String priceTireFitting_sedan, String priceTireFitting_business, String priceTireFitting_premium, String priceTireFitting_SUV, String priceTireFitting_BigSUV) {
        this.idTireFitting = idTireFitting;
        this.titleTireFitting = titleTireFitting;
        this.priceTireFitting_sedan = priceTireFitting_sedan;
        this.priceTireFitting_business = priceTireFitting_business;
        this.priceTireFitting_premium = priceTireFitting_premium;
        this.priceTireFitting_SUV = priceTireFitting_SUV;
        this.priceTireFitting_BigSUV = priceTireFitting_BigSUV;
    }

    public String getIdTireFitting() {
        return idTireFitting;
    }

    public void setIdTireFitting(String idTireFitting) {
        this.idTireFitting = idTireFitting;
    }

    public String getTitleTireFitting() {
        return titleTireFitting;
    }

    public void setTitleTireFitting(String titleTireFitting) {
        this.titleTireFitting = titleTireFitting;
    }

    public String getPriceTireFitting_sedan() {
        return priceTireFitting_sedan;
    }

    public void setPriceTireFitting_sedan(String priceTireFitting_sedan) {
        this.priceTireFitting_sedan = priceTireFitting_sedan;
    }

    public String getPriceTireFitting_business() {
        return priceTireFitting_business;
    }

    public void setPriceTireFitting_business(String priceTireFitting_business) {
        this.priceTireFitting_business = priceTireFitting_business;
    }

    public String getPriceTireFitting_premium() {
        return priceTireFitting_premium;
    }

    public void setPriceTireFitting_premium(String priceTireFitting_premium) {
        this.priceTireFitting_premium = priceTireFitting_premium;
    }

    public String getPriceTireFitting_SUV() {
        return priceTireFitting_SUV;
    }

    public void setPriceTireFitting_SUV(String priceTireFitting_SUV) {
        this.priceTireFitting_SUV = priceTireFitting_SUV;
    }

    public String getPriceTireFitting_BigSUV() {
        return priceTireFitting_BigSUV;
    }

    public void setPriceTireFitting_BigSUV(String priceTireFitting_BigSUV) {
        this.priceTireFitting_BigSUV = priceTireFitting_BigSUV;
    }
}

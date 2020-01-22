package com.example.client.Models;

public class SalonProtection {
    public String idSalonProtection;
    public String titleSalonProtection;
    public String priceSalonProtection_sedan;
    public String priceSalonProtection_business;
    public String priceSalonProtection_premium;
    public String priceSalonProtection_SUV;
    public String priceSalonProtection_BigSUV;

    public SalonProtection() {
    }

    public SalonProtection(String idSalonProtection, String titleSalonProtection, String priceSalonProtection_sedan, String priceSalonProtection_business, String priceSalonProtection_premium, String priceSalonProtection_SUV, String priceSalonProtection_BigSUV) {
        this.idSalonProtection = idSalonProtection;
        this.titleSalonProtection = titleSalonProtection;
        this.priceSalonProtection_sedan = priceSalonProtection_sedan;
        this.priceSalonProtection_business = priceSalonProtection_business;
        this.priceSalonProtection_premium = priceSalonProtection_premium;
        this.priceSalonProtection_SUV = priceSalonProtection_SUV;
        this.priceSalonProtection_BigSUV = priceSalonProtection_BigSUV;
    }

    public String getIdSalonProtection() {
        return idSalonProtection;
    }

    public void setIdSalonProtection(String idSalonProtection) {
        this.idSalonProtection = idSalonProtection;
    }

    public String getTitleSalonProtection() {
        return titleSalonProtection;
    }

    public void setTitleSalonProtection(String titleSalonProtection) {
        this.titleSalonProtection = titleSalonProtection;
    }

    public String getPriceSalonProtection_sedan() {
        return priceSalonProtection_sedan;
    }

    public void setPriceSalonProtection_sedan(String priceSalonProtection_sedan) {
        this.priceSalonProtection_sedan = priceSalonProtection_sedan;
    }

    public String getPriceSalonProtection_business() {
        return priceSalonProtection_business;
    }

    public void setPriceSalonProtection_business(String priceSalonProtection_business) {
        this.priceSalonProtection_business = priceSalonProtection_business;
    }

    public String getPriceSalonProtection_premium() {
        return priceSalonProtection_premium;
    }

    public void setPriceSalonProtection_premium(String priceSalonProtection_premium) {
        this.priceSalonProtection_premium = priceSalonProtection_premium;
    }

    public String getPriceSalonProtection_SUV() {
        return priceSalonProtection_SUV;
    }

    public void setPriceSalonProtection_SUV(String priceSalonProtection_SUV) {
        this.priceSalonProtection_SUV = priceSalonProtection_SUV;
    }

    public String getPriceSalonProtection_BigSUV() {
        return priceSalonProtection_BigSUV;
    }

    public void setPriceSalonProtection_BigSUV(String priceSalonProtection_BigSUV) {
        this.priceSalonProtection_BigSUV = priceSalonProtection_BigSUV;
    }
}

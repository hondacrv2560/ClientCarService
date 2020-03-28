package com.example.client.Models;

public class RepairWindshield {
    public String idRepairWindshield;
    public String titleRepairWindshield;
    public String priceRepairWindshield_sedan;
    public String priceRepairWindshield_business;
    public String priceRepairWindshield_premium;
    public String priceRepairWindshield_SUV;
    public String priceRepairWindshield_BigSUV;

    public RepairWindshield() {
    }

    public RepairWindshield(String idRepairWindshield, String titleRepairWindshield, String priceRepairWindshield_sedan, String priceRepairWindshield_business, String priceRepairWindshield_premium, String priceRepairWindshield_SUV, String priceRepairWindshield_BigSUV) {
        this.idRepairWindshield = idRepairWindshield;
        this.titleRepairWindshield = titleRepairWindshield;
        this.priceRepairWindshield_sedan = priceRepairWindshield_sedan;
        this.priceRepairWindshield_business = priceRepairWindshield_business;
        this.priceRepairWindshield_premium = priceRepairWindshield_premium;
        this.priceRepairWindshield_SUV = priceRepairWindshield_SUV;
        this.priceRepairWindshield_BigSUV = priceRepairWindshield_BigSUV;
    }

    public String getIdRepairWindshield() {
        return idRepairWindshield;
    }

    public void setIdRepairWindshield(String idRepairWindshield) {
        this.idRepairWindshield = idRepairWindshield;
    }

    public String getTitleRepairWindshield() {
        return titleRepairWindshield;
    }

    public void setTitleRepairWindshield(String titleRepairWindshield) {
        this.titleRepairWindshield = titleRepairWindshield;
    }

    public String getPriceRepairWindshield_sedan() {
        return priceRepairWindshield_sedan;
    }

    public void setPriceRepairWindshield_sedan(String priceRepairWindshield_sedan) {
        this.priceRepairWindshield_sedan = priceRepairWindshield_sedan;
    }

    public String getPriceRepairWindshield_business() {
        return priceRepairWindshield_business;
    }

    public void setPriceRepairWindshield_business(String priceRepairWindshield_business) {
        this.priceRepairWindshield_business = priceRepairWindshield_business;
    }

    public String getPriceRepairWindshield_premium() {
        return priceRepairWindshield_premium;
    }

    public void setPriceRepairWindshield_premium(String priceRepairWindshield_premium) {
        this.priceRepairWindshield_premium = priceRepairWindshield_premium;
    }

    public String getPriceRepairWindshield_SUV() {
        return priceRepairWindshield_SUV;
    }

    public void setPriceRepairWindshield_SUV(String priceRepairWindshield_SUV) {
        this.priceRepairWindshield_SUV = priceRepairWindshield_SUV;
    }

    public String getPriceRepairWindshield_BigSUV() {
        return priceRepairWindshield_BigSUV;
    }

    public void setPriceRepairWindshield_BigSUV(String priceRepairWindshield_BigSUV) {
        this.priceRepairWindshield_BigSUV = priceRepairWindshield_BigSUV;
    }
}

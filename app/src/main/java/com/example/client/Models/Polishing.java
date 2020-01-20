package com.example.client.Models;

public class Polishing {
    public String idPolishing;
    public String titlePolishing;
    public String pricePolishing_sedan;
    public String pricePolishing_business;
    public String pricePolishing_premium;
    public String pricePolishing_SUV;
    public String pricePolishing_BigSUV;

    public Polishing() {
    }

    public Polishing(String idPolishing, String titlePolishing, String pricePolishing_sedan, String pricePolishing_business, String pricePolishing_premium, String pricePolishing_SUV, String pricePolishing_BigSUV) {
        this.idPolishing = idPolishing;
        this.titlePolishing = titlePolishing;
        this.pricePolishing_sedan = pricePolishing_sedan;
        this.pricePolishing_business = pricePolishing_business;
        this.pricePolishing_premium = pricePolishing_premium;
        this.pricePolishing_SUV = pricePolishing_SUV;
        this.pricePolishing_BigSUV = pricePolishing_BigSUV;
    }

    public String getIdPolishing() {
        return idPolishing;
    }

    public void setIdPolishing(String idPolishing) {
        this.idPolishing = idPolishing;
    }

    public String getTitlePolishing() {
        return titlePolishing;
    }

    public void setTitlePolishing(String titlePolishing) {
        this.titlePolishing = titlePolishing;
    }

    public String getPricePolishing_sedan() {
        return pricePolishing_sedan;
    }

    public void setPricePolishing_sedan(String pricePolishing_sedan) {
        this.pricePolishing_sedan = pricePolishing_sedan;
    }

    public String getPricePolishing_business() {
        return pricePolishing_business;
    }

    public void setPricePolishing_business(String pricePolishing_business) {
        this.pricePolishing_business = pricePolishing_business;
    }

    public String getPricePolishing_premium() {
        return pricePolishing_premium;
    }

    public void setPricePolishing_premium(String pricePolishing_premium) {
        this.pricePolishing_premium = pricePolishing_premium;
    }

    public String getPricePolishing_SUV() {
        return pricePolishing_SUV;
    }

    public void setPricePolishing_SUV(String pricePolishing_SUV) {
        this.pricePolishing_SUV = pricePolishing_SUV;
    }

    public String getPricePolishing_BigSUV() {
        return pricePolishing_BigSUV;
    }

    public void setPricePolishing_BigSUV(String pricePolishing_BigSUV) {
        this.pricePolishing_BigSUV = pricePolishing_BigSUV;
    }
}

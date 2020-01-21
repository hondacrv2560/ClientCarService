package com.example.client.Models;

public class ProtectiveFilm {
    public String idProtectiveFilm;
    public String titleProtectiveFilm;
    public String priceProtectiveFilm_sedan;
    public String priceProtectiveFilm_business;
    public String priceProtectiveFilm_premium;
    public String priceProtectiveFilm_SUV;
    public String priceProtectiveFilm_BigSUV;

    public ProtectiveFilm() {
    }

    public ProtectiveFilm(String idProtectiveFilm, String titleProtectiveFilm, String priceProtectiveFilm_sedan, String priceProtectiveFilm_business, String priceProtectiveFilm_premium, String priceProtectiveFilm_SUV, String priceProtectiveFilm_BigSUV) {
        this.idProtectiveFilm = idProtectiveFilm;
        this.titleProtectiveFilm = titleProtectiveFilm;
        this.priceProtectiveFilm_sedan = priceProtectiveFilm_sedan;
        this.priceProtectiveFilm_business = priceProtectiveFilm_business;
        this.priceProtectiveFilm_premium = priceProtectiveFilm_premium;
        this.priceProtectiveFilm_SUV = priceProtectiveFilm_SUV;
        this.priceProtectiveFilm_BigSUV = priceProtectiveFilm_BigSUV;
    }

    public String getIdProtectiveFilm() {
        return idProtectiveFilm;
    }

    public void setIdProtectiveFilm(String idProtectiveFilm) {
        this.idProtectiveFilm = idProtectiveFilm;
    }

    public String getTitleProtectiveFilm() {
        return titleProtectiveFilm;
    }

    public void setTitleProtectiveFilm(String titleProtectiveFilm) {
        this.titleProtectiveFilm = titleProtectiveFilm;
    }

    public String getPriceProtectiveFilm_sedan() {
        return priceProtectiveFilm_sedan;
    }

    public void setPriceProtectiveFilm_sedan(String priceProtectiveFilm_sedan) {
        this.priceProtectiveFilm_sedan = priceProtectiveFilm_sedan;
    }

    public String getPriceProtectiveFilm_business() {
        return priceProtectiveFilm_business;
    }

    public void setPriceProtectiveFilm_business(String priceProtectiveFilm_business) {
        this.priceProtectiveFilm_business = priceProtectiveFilm_business;
    }

    public String getPriceProtectiveFilm_premium() {
        return priceProtectiveFilm_premium;
    }

    public void setPriceProtectiveFilm_premium(String priceProtectiveFilm_premium) {
        this.priceProtectiveFilm_premium = priceProtectiveFilm_premium;
    }

    public String getPriceProtectiveFilm_SUV() {
        return priceProtectiveFilm_SUV;
    }

    public void setPriceProtectiveFilm_SUV(String priceProtectiveFilm_SUV) {
        this.priceProtectiveFilm_SUV = priceProtectiveFilm_SUV;
    }

    public String getPriceProtectiveFilm_BigSUV() {
        return priceProtectiveFilm_BigSUV;
    }

    public void setPriceProtectiveFilm_BigSUV(String priceProtectiveFilm_BigSUV) {
        this.priceProtectiveFilm_BigSUV = priceProtectiveFilm_BigSUV;
    }
}

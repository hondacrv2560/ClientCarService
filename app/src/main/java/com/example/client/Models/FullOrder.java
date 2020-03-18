package com.example.client.Models;

public class FullOrder {
    public int idService;
    public String titleService;
    public int price_BigSUV;
    public int price_SUV;
    public int price_business;
    public int price_premium;
    public int price_sedan;
    public String cat_BigSUV;
    public String cat_SUV;
    public String cat_business;
    public String cat_premium;
    public String cat_sedan;
    public boolean expandable;

    public FullOrder() {
    }

    public FullOrder(int idService, String titleService, int price_BigSUV, int price_SUV, int price_business, int price_premium, int price_sedan, String cat_BigSUV, String cat_SUV, String cat_business, String cat_premium, String cat_sedan, boolean expandable) {
        this.idService = idService;
        this.titleService = titleService;
        this.price_BigSUV = price_BigSUV;
        this.price_SUV = price_SUV;
        this.price_business = price_business;
        this.price_premium = price_premium;
        this.price_sedan = price_sedan;
        this.cat_BigSUV = cat_BigSUV;
        this.cat_SUV = cat_SUV;
        this.cat_business = cat_business;
        this.cat_premium = cat_premium;
        this.cat_sedan = cat_sedan;
        this.expandable = expandable;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getTitleService() {
        return titleService;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public int getPrice_BigSUV() {
        return price_BigSUV;
    }

    public void setPrice_BigSUV(int price_BigSUV) {
        this.price_BigSUV = price_BigSUV;
    }

    public int getPrice_SUV() {
        return price_SUV;
    }

    public void setPrice_SUV(int price_SUV) {
        this.price_SUV = price_SUV;
    }

    public int getPrice_business() {
        return price_business;
    }

    public void setPrice_business(int price_business) {
        this.price_business = price_business;
    }

    public int getPrice_premium() {
        return price_premium;
    }

    public void setPrice_premium(int price_premium) {
        this.price_premium = price_premium;
    }

    public int getPrice_sedan() {
        return price_sedan;
    }

    public void setPrice_sedan(int price_sedan) {
        this.price_sedan = price_sedan;
    }

    public String getCat_BigSUV() {
        return cat_BigSUV;
    }

    public void setCat_BigSUV(String cat_BigSUV) {
        this.cat_BigSUV = cat_BigSUV;
    }

    public String getCat_SUV() {
        return cat_SUV;
    }

    public void setCat_SUV(String cat_SUV) {
        this.cat_SUV = cat_SUV;
    }

    public String getCat_business() {
        return cat_business;
    }

    public void setCat_business(String cat_business) {
        this.cat_business = cat_business;
    }

    public String getCat_premium() {
        return cat_premium;
    }

    public void setCat_premium(String cat_premium) {
        this.cat_premium = cat_premium;
    }

    public String getCat_sedan() {
        return cat_sedan;
    }

    public void setCat_sedan(String cat_sedan) {
        this.cat_sedan = cat_sedan;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}

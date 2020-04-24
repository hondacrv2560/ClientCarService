package com.example.client.Models;

import java.sql.Time;
import java.util.Date;

public class FullOrders {

    public String idService;
    public String titleService;
    public String category;
    public int priceService;
    public String idClient;
    public String idOrder;
    public String currentDate;
    public String currentTime;

    public FullOrders() {
    }

    public FullOrders(String idService, String titleService, String category, int priceService, String idClient, String idOrder, String currentDate, String currentTime) {
        this.idService = idService;
        this.titleService = titleService;
        this.category = category;
        this.priceService = priceService;
        this.idClient = idClient;
        this.idOrder = idOrder;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getTitleService() {
        return titleService;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}

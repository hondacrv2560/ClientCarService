package com.example.client.Models;

public class FullOrders {

    public String idService;
    public String titleService;
    public String priceService;

    public FullOrders() {
    }

    public FullOrders(String idService, String titleService, String priceService) {
        this.idService = idService;
        this.titleService = titleService;
        this.priceService = priceService;
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

    public String getPriceService() {
        return priceService;
    }

    public void setPriceService(String priceService) {
        this.priceService = priceService;
    }
}

package com.example.client.Models;

public class FullOrders {

    public String idService;
    public String titleService;
    public int priceService;
    public String idClient;
    public String idOrder;

    public FullOrders() {
    }

    public FullOrders(String idService, String titleService, int priceService, String idClient, String idOrder) {
        this.idService = idService;
        this.titleService = titleService;
        this.priceService = priceService;
        this.idClient = idClient;
        this.idOrder = idOrder;
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
}

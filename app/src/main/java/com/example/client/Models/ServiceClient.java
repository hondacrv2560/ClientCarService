package com.example.client.Models;

public class ServiceClient {
    public String idService;
    public String title_service;

    public ServiceClient() {
    }

    public ServiceClient(String idService, String title_service) {
        this.idService = idService;
        this.title_service = title_service;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getTitle_service() {
        return title_service;
    }

    public void setTitle_service(String title_service) {
        this.title_service = title_service;
    }
}

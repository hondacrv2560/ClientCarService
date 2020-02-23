package com.example.client.Models;

public class OrderViewClient {
    public String idUser;
    public int idService;
    public int titleService;
    public int hourService;
    public int minService;
    public int dayService;
    public int monthService;
    public int yearService;

    public OrderViewClient() {
    }

    public OrderViewClient(String idUser, int idService, int titleService, int hourService, int minService, int dayService, int monthService, int yearService) {
        this.idUser = idUser;
        this.idService = idService;
        this.titleService = titleService;
        this.hourService = hourService;
        this.minService = minService;
        this.dayService = dayService;
        this.monthService = monthService;
        this.yearService = yearService;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getTitleService() {
        return titleService;
    }

    public void setTitleService(int titleService) {
        this.titleService = titleService;
    }

    public int getHourService() {
        return hourService;
    }

    public void setHourService(int hourService) {
        this.hourService = hourService;
    }

    public int getMinService() {
        return minService;
    }

    public void setMinService(int minService) {
        this.minService = minService;
    }

    public int getDayService() {
        return dayService;
    }

    public void setDayService(int dayService) {
        this.dayService = dayService;
    }

    public int getMonthService() {
        return monthService;
    }

    public void setMonthService(int monthService) {
        this.monthService = monthService;
    }

    public int getYearService() {
        return yearService;
    }

    public void setYearService(int yearService) {
        this.yearService = yearService;
    }
}

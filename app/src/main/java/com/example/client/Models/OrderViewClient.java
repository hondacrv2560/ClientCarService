package com.example.client.Models;

public class OrderViewClient {
//    public String UserId;
    public int idService;
    public int titleService;
    public int startTimeHour;
    public int startTimeMinute;
    public int startDayOfMonth;
    public int startTimeMonth;
    public int startTimeYear;

    public OrderViewClient() {
    }

    public OrderViewClient(int idService, int titleService, int startTimeHour, int startTimeMinute, int startDayOfMonth, int startTimeMonth, int startTimeYear) {
        this.idService = idService;
        this.titleService = titleService;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.startDayOfMonth = startDayOfMonth;
        this.startTimeMonth = startTimeMonth;
        this.startTimeYear = startTimeYear;
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

    public int getStartTimeHour() {
        return startTimeHour;
    }

    public void setStartTimeHour(int startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    public int getStartTimeMinute() {
        return startTimeMinute;
    }

    public void setStartTimeMinute(int startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    public int getStartDayOfMonth() {
        return startDayOfMonth;
    }

    public void setStartDayOfMonth(int startDayOfMonth) {
        this.startDayOfMonth = startDayOfMonth;
    }

    public int getStartTimeMonth() {
        return startTimeMonth;
    }

    public void setStartTimeMonth(int startTimeMonth) {
        this.startTimeMonth = startTimeMonth;
    }

    public int getStartTimeYear() {
        return startTimeYear;
    }

    public void setStartTimeYear(int startTimeYear) {
        this.startTimeYear = startTimeYear;
    }
}

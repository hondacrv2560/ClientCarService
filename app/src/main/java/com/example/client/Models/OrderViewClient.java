package com.example.client.Models;

public class OrderViewClient {
//    public String UserId;
    public int idService;
    public String titleService;
    public String month;
    public int startTimeHour;
    public int startTimeMinute;
    public int startDayOfMonth;
    public int startTimeMonth;
    public int startTimeYear;

    public OrderViewClient() {
    }

    public OrderViewClient(int idService, String titleService, int startTimeHour, int startTimeMinute, int startDayOfMonth, int startTimeMonth, int startTimeYear, String month) {
        this.idService = idService;
        this.titleService = titleService;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.startDayOfMonth = startDayOfMonth;
        this.startTimeMonth = startTimeMonth;
        this.startTimeYear = startTimeYear;
        this.month = month;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

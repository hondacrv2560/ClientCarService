package com.example.client.Models;

public class OrderViewClient {

    public String UserId;
    public int startTimeHour;
    public int startTimeMinute;
    public int startDayOfMonth;
    public int startTimeMonth;
    public int startTimeYear;
    public int endTimeHour;
    public int endTimeMinute;
    public int endDayOfMonth;
    public int endTimeMonth;
    public int endTimeYear;
    public String color;
    public int idService;

    public OrderViewClient() {
    }

    public OrderViewClient(String userId, int startTimeHour, int startTimeMinute, int startDayOfMonth, int startTimeMonth, int startTimeYear, int endTimeHour, int endTimeMinute, int endDayOfMonth, int endTimeMonth, int endTimeYear, String color, int idService) {
        this.UserId = userId;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.startDayOfMonth = startDayOfMonth;
        this.startTimeMonth = startTimeMonth;
        this.startTimeYear = startTimeYear;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.endDayOfMonth = endDayOfMonth;
        this.endTimeMonth = endTimeMonth;
        this.endTimeYear = endTimeYear;
        this.color = color;
        this.idService = idService;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public void setEndTimeHour(int endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    public int getEndTimeMinute() {
        return endTimeMinute;
    }

    public void setEndTimeMinute(int endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
    }

    public int getEndDayOfMonth() {
        return endDayOfMonth;
    }

    public void setEndDayOfMonth(int endDayOfMonth) {
        this.endDayOfMonth = endDayOfMonth;
    }

    public int getEndTimeMonth() {
        return endTimeMonth;
    }

    public void setEndTimeMonth(int endTimeMonth) {
        this.endTimeMonth = endTimeMonth;
    }

    public int getEndTimeYear() {
        return endTimeYear;
    }

    public void setEndTimeYear(int endTimeYear) {
        this.endTimeYear = endTimeYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }
}

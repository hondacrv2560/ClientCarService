package com.example.client.Models;

public class EventOrder {
    private String name;
    private int dayOfMonth;
    private String startTime;
    private String endTime;
    private String color;

    public EventOrder() {
    }

    public EventOrder(String name, int dayOfMonth, String startTime, String endTime, String color) {
        this.name = name;
        this.dayOfMonth = dayOfMonth;
        this.startTime = startTime;
        this.endTime = endTime;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

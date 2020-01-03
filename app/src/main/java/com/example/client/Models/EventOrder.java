package com.example.client.Models;

public class EventOrder {
    private String mName;
    private int mDayOfMonth;
    private String mStartTime;
    private String mEndTime;
    private String mColor;

    public EventOrder() {
    }

    public EventOrder(String mName, int mDayOfMonth, String mStartTime, String mEndTime, String mColor) {
        this.mName = mName;
        this.mDayOfMonth = mDayOfMonth;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mColor = mColor;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmDayOfMonth() {
        return mDayOfMonth;
    }

    public void setmDayOfMonth(int mDayOfMonth) {
        this.mDayOfMonth = mDayOfMonth;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }
}

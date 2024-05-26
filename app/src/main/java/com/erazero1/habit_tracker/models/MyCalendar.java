package com.erazero1.habit_tracker.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {
    // storing day - like Sun, Wed etc..,
    // date like 1, 2 etc..
    //Month 1..12
    // year .. 2019.. beyond

    private String day;
    private String date, month, year;
    private int pos;

    public MyCalendar() {
    }

    public MyCalendar(String day, String date, String month, String year, int i) {
        this.day = day;
        this.date = date;
        this.month = getMonthStr(month);
        this.year = year;
        this.pos =i;

    }
    private String getMonthStr(String month){

        Calendar cal=Calendar.getInstance();

        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        int monthNum=Integer.parseInt(month);
        cal.set(Calendar.MONTH,monthNum);
        String monthName = month_date.format(cal.getTime());
        return monthName;

    }
    public int getPos() {
        return pos;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String date) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public String getMonth() {
        return month;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }


}


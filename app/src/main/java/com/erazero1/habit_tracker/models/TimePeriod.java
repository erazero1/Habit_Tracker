package com.erazero1.habit_tracker.models;

import android.text.format.DateUtils;

public class TimePeriod {
    private int startHours;
    private int startMinutes;
    private int endHours;
    private int endMinutes;

    public TimePeriod(int startHours, int startMinutes){
        this.startHours = startHours;
        this.startMinutes = startMinutes;
    }

    public TimePeriod(int startHours, int startMinutes, int endHours, int endMinutes) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
    }

    public int getStartHours() {
        return startHours;
    }

    public long getStartHoursInMillis() {
        return startHours * DateUtils.HOUR_IN_MILLIS;
    }

    public void setStartHours(int hours) {
        this.startHours = hours;
    }

    public void setStartHoursInMillis(long hoursInMillis) {
        this.startHours = (int) (hoursInMillis / DateUtils.HOUR_IN_MILLIS);
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public long getStartMinuteInMillis() {
        return startMinutes * DateUtils.MINUTE_IN_MILLIS;
    }

    public void setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
    }

    public void setStartMinutesInMillis(long startMinutesInMillis) {
        this.startMinutes = (int) (startMinutesInMillis / DateUtils.MINUTE_IN_MILLIS);
    }

    public int getEndHours() {
        return endHours;
    }

    public long getEndHoursInMillis() {
        return endHours * DateUtils.HOUR_IN_MILLIS;
    }

    public void setEndHours(int endHours) {
        this.endHours = endHours;
    }

    public void setEndHoursInMillis(long endHoursInMillis) {
        this.endHours = (int) (endHoursInMillis * DateUtils.HOUR_IN_MILLIS);
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public long getEndMinutesInMillis() {
        return endMinutes * DateUtils.MINUTE_IN_MILLIS;
    }

    public void setEndMinutes(int endMinutes) {
        this.endMinutes = endMinutes;
    }

    public void setEndMinutesInMillis(long endMinutesInMillis) {
        this.endMinutes = (int) (endMinutesInMillis / DateUtils.MINUTE_IN_MILLIS);
    }

    public long getStartTimeInMillis(){
        return getStartHoursInMillis() + getStartMinuteInMillis();
    }

    public long getEndTimeInMillis(){
        return getEndHoursInMillis() + getEndMinutesInMillis();
    }

}

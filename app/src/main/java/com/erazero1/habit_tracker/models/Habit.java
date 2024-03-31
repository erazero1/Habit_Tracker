package com.erazero1.habit_tracker.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Habit implements Serializable {
    private static final int REGULAR_HABIT = 0;
    private static final int NEGATIVE_HABIT = 1;
    private static final int ONE_TIME_TO_DO_HABIT = 2;
    private String iconUri;
    private String iconColor;
    private ArrayList<String> repeatDays;
    private String doItAt;
    private String goal;
    private int duration;
    private byte repeats;
    private boolean reminderForThisHabit = false;
    private String endOn;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Habit(){}
    public Habit(String iconUri, String iconColor, ArrayList<String> repeatDays, String doItAt, String goal, int duration, boolean reminderForThisHabit, String endOn) {
        this.iconUri = iconUri;
        this.iconColor = iconColor;
        this.repeatDays = repeatDays;
        this.doItAt = doItAt;
        this.goal = goal;
        this.duration = duration;
        this.reminderForThisHabit = reminderForThisHabit;
        this.endOn = endOn;
    }

    public Habit(String iconUri, String iconColor, ArrayList<String> repeatDays, String doItAt, String goal, byte repeats, boolean reminderForThisHabit, String endOn) {
        this.iconUri = iconUri;
        this.iconColor = iconColor;
        this.repeatDays = repeatDays;
        this.doItAt = doItAt;
        this.goal = goal;
        this.repeats = repeats;
        this.reminderForThisHabit = reminderForThisHabit;
        this.endOn = endOn;
    }

    public Habit(String iconUri, String iconColor, ArrayList<String> repeatDays, String doItAt, String goal, boolean reminderForThisHabit, String endOn) {
        this.iconUri = iconUri;
        this.iconColor = iconColor;
        this.repeatDays = repeatDays;
        this.doItAt = doItAt;
        this.goal = goal;
        this.reminderForThisHabit = reminderForThisHabit;
        this.endOn = endOn;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public ArrayList<String> getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(ArrayList<String> repeatDays) {
        this.repeatDays = repeatDays;
    }

    public String getDoItAt() {
        return doItAt;
    }

    public void setDoItAt(String doItAt) {
        this.doItAt = doItAt;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(byte repeats) {
        this.repeats = repeats;
    }

    public boolean isReminderForThisHabit() {
        return reminderForThisHabit;
    }

    public void setReminderForThisHabit(boolean reminderForThisHabit) {
        this.reminderForThisHabit = reminderForThisHabit;
    }

    public String getEndOn() {
        return endOn;
    }

    public void setEndOn(String endOn) {
        this.endOn = endOn;
    }
}

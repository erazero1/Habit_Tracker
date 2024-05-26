package com.erazero1.habit_tracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

@Entity(tableName = "habit")
public class Habit implements Serializable {
    public static final int REGULAR_HABIT = 0;
    public static final int NEGATIVE_HABIT = 1;
    public static final int ONE_TIME_TO_DO_HABIT = 2;

    public static final String DO_IT_AT_ANYTIME = "ANYTIME";
    public static final String DO_IT_AT_MORNING = "MORNING";
    public static final String DO_IT_AT_AFTERNOON = "AFTERNOON";
    public static final String DO_IT_AT_EVENING = "EVENING";

    @ColumnInfo(name = "name_of_habit")
    private String nameOfHabit;
    @ColumnInfo(name = "type_of_habit")
    private int typeOfHabit;
    @ColumnInfo(name = "habit_id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "icon_id")
    private int iconId;
    @ColumnInfo(name = "icon_color")
    private String iconColor;

    @ColumnInfo(name = "repeat_days")

    private ArrayList<RepeatDays> repeatDays;
    @ColumnInfo(name = "do_it_at")
    private String doItAt; //Anytime, Morning, Afternoon, Evening

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @ColumnInfo(name = "goal")
    private String goal;
    @ColumnInfo(name = "duration")
    private int duration;
    @ColumnInfo(name = "repeats")
    private int repeats;
    @ColumnInfo(name = "reminder_for_this_habit")
    private boolean reminderForThisHabit = false;
    @ColumnInfo(name = "reminderHour")
    private int reminderHour;
    @ColumnInfo(name = "reminderMinute")
    private int reminderMinute;

    public int getReminderHour() {
        return reminderHour;
    }

    public void setReminderHour(int reminderHour) {
        this.reminderHour = reminderHour;
    }

    public int getReminderMinute() {
        return reminderMinute;
    }

    public void setReminderMinute(int reminderMinute) {
        this.reminderMinute = reminderMinute;
    }

    @ColumnInfo(name = "end_on")
    private String endOn;
    @ColumnInfo(name = "is_checked")
    private boolean isChecked = false;
    @ColumnInfo(name = "created_time")
    private long createdTime;



    @Ignore
    public Habit(String nameOfHabit, int duration, boolean isChecked) {
        this.nameOfHabit = nameOfHabit;
        this.duration = duration;
        this.isChecked = isChecked;
        this.createdTime = Calendar.getInstance().getTimeInMillis();
    }


    public int getKey() {
        return id;
    }

    public Habit() {
        this.createdTime = Calendar.getInstance().getTimeInMillis();
    }

    @Ignore
    public Habit(int iconId, int typeOfHabit, String nameOfHabit, String iconColor, ArrayList<RepeatDays> repeatDays, String doItAt, String goal, int duration, boolean reminderForThisHabit, String endOn) {
        this.iconId = iconId;
        this.nameOfHabit = nameOfHabit;
        this.typeOfHabit = typeOfHabit;
        this.iconColor = iconColor;
        this.repeatDays = repeatDays;
        this.doItAt = doItAt;
        this.createdTime = Calendar.getInstance().getTimeInMillis();
        this.goal = goal;
        this.duration = duration;
        this.reminderForThisHabit = reminderForThisHabit;
        this.endOn = endOn;
    }

    @Ignore
    public Habit(int iconId, String iconColor, ArrayList<RepeatDays> repeatDays, String doItAt, String goal, int repeats, boolean reminderForThisHabit, String endOn) {
        this.iconId = iconId;
        this.iconColor = iconColor;
        this.repeatDays = repeatDays;
        this.doItAt = doItAt;
        this.goal = goal;
        this.createdTime = Calendar.getInstance().getTimeInMillis();
        this.repeats = repeats;
        this.reminderForThisHabit = reminderForThisHabit;
        this.endOn = endOn;
    }

    @Ignore
    public Habit(int iconId, String iconColor, ArrayList<RepeatDays> repeatDays, String doItAt, String goal, boolean reminderForThisHabit, String endOn) {
        this.iconId = iconId;
        this.createdTime = Calendar.getInstance().getTimeInMillis();
        this.iconColor = iconColor;
        this.repeatDays = repeatDays;
        this.doItAt = doItAt;
        this.goal = goal;
        this.reminderForThisHabit = reminderForThisHabit;
        this.endOn = endOn;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public ArrayList<RepeatDays> getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(ArrayList<RepeatDays> repeatDays) {
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

    public String getNameOfHabit() {
        return nameOfHabit;
    }

    public int getTypeOfHabit() {
        return typeOfHabit;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setNameOfHabit(String nameOfHabit) {
        this.nameOfHabit = nameOfHabit;
    }

    public void setTypeOfHabit(int typeOfHabit) {
        this.typeOfHabit = typeOfHabit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}

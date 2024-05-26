package com.erazero1.habit_tracker.models;

public class MyIcon {
    private int resId;
    private String name;

    public MyIcon(){}

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyIcon(String name, int resId){
        this.name = name;
        this.resId = resId;
    }
}

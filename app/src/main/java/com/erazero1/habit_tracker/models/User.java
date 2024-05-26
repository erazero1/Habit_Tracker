package com.erazero1.habit_tracker.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable {
    public static final int CLIENT_INDICATOR = 0;
    public static final int TRANSPORTER_INDICATOR = 1;
    public static final int ADMINISTRATION_INDICATOR = 2;
    private String email;
    private String passwd;
    private String name;
    private String username;
    private String phone;
    private String profileDescription;
    private String avatarUri;
    private int age;
    private int userIndicator;
    private String UID;
    private String key;
    private boolean isEmailPrivate = true;
    private boolean isPhonePrivate = true;
    private long createdTimeInMillis;

    public User(){}


    public String getUsername() {
        return username;
    }

    public User(String email, String passwd, String username, String name, String UID, String key) {
        this.email = email;
        this.passwd = passwd;
        this.username = username;
        this.name = name;
        this.UID = UID;
        this.key = key;
        this.createdTimeInMillis = Calendar.getInstance().getTimeInMillis();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserIndicator() {
        return userIndicator;
    }

    public void setUserIndicator(int userIndicator) {
        this.userIndicator = userIndicator;
    }

    public boolean isEmailPrivate() {
        return isEmailPrivate;
    }

    public void setEmailPrivate(boolean emailPrivate) {
        isEmailPrivate = emailPrivate;
    }

    public boolean isPhonePrivate() {
        return isPhonePrivate;
    }

    public void setPhonePrivate(boolean phonePrivate) {
        isPhonePrivate = phonePrivate;
    }


    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    @Exclude
    public Calendar getCreatedDateTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.createdTimeInMillis);
        return calendar;
    }
}

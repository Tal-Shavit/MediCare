package com.example.medicare.Model;

import android.net.Uri;

import java.time.LocalTime;

public class PillItem {

    private String namePill;
    private String timeToTake;
    private int countToTake;
    private Boolean sunday;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;

    public PillItem() {
    }

    public String getNamePill() {
        return namePill;
    }

    public PillItem setNamePill(String namePill) {
        this.namePill = namePill;
        return this;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public PillItem setTimeToTake(String timeToTake) {
        this.timeToTake = timeToTake;
        return this;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public PillItem setSunday(Boolean sunday) {
        this.sunday = sunday;
        return this;
    }

    public Boolean getMonday() {
        return monday;
    }

    public PillItem setMonday(Boolean monday) {
        this.monday = monday;
        return this;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public PillItem setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
        return this;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public PillItem setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
        return this;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public PillItem setThursday(Boolean thursday) {
        this.thursday = thursday;
        return this;
    }

    public Boolean getFriday() {
        return friday;
    }

    public PillItem setFriday(Boolean friday) {
        this.friday = friday;
        return this;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public PillItem setSaturday(Boolean saturday) {
        this.saturday = saturday;
        return this;
    }

    public int getCountToTake() {
        return countToTake;
    }

    public PillItem setCountToTake(int countToTake) {
        this.countToTake = countToTake;
        return this;
    }

    public LocalTime convertStringToTime(String time){
        String arr[] = time.split(":");
        return LocalTime.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }

}

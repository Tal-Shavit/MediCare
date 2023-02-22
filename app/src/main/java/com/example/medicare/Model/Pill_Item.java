package com.example.medicare.Model;

import android.widget.ImageView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Pill_Item {

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

    //private ImageView imgPill;

    public Pill_Item() {
    }

//    public ImageView getImgPill() {
//        return imgPill;
//    }
//
//    public Pill_Item setImgPill(ImageView imgPill) {
//        this.imgPill = imgPill;
//        return this;
//    }

    public String getNamePill() {
        return namePill;
    }

    public Pill_Item setNamePill(String namePill) {
        this.namePill = namePill;
        return this;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public Pill_Item setTimeToTake(String timeToTake) {
        this.timeToTake = timeToTake;
        return this;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public Pill_Item setSunday(Boolean sunday) {
        this.sunday = sunday;
        return this;
    }

    public Boolean getMonday() {
        return monday;
    }

    public Pill_Item setMonday(Boolean monday) {
        this.monday = monday;
        return this;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public Pill_Item setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
        return this;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public Pill_Item setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
        return this;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public Pill_Item setThursday(Boolean thursday) {
        this.thursday = thursday;
        return this;
    }

    public Boolean getFriday() {
        return friday;
    }

    public Pill_Item setFriday(Boolean friday) {
        this.friday = friday;
        return this;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public Pill_Item setSaturday(Boolean saturday) {
        this.saturday = saturday;
        return this;
    }

    /*public HashMap<String, String> getDayToTake() {
        return dayToTake;
    }

    public Pill_Item setDayToTake(HashMap<String, String> dayToTake) {
        this.dayToTake = dayToTake;
        return this;
    }*/

    public int getCountToTake() {
        return countToTake;
    }

    public Pill_Item setCountToTake(int countToTake) {
        this.countToTake = countToTake;
        return this;
    }

    public LocalTime convertStringToTime(String time){
        String arr[] = time.split(":");
        return LocalTime.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }

    /*public ImageView getImgPill() {
        return imgPill;
    }

    public Pill_Item setImgPill(ImageView imgPill) {
        this.imgPill = imgPill;
        return this;
    }*/
}

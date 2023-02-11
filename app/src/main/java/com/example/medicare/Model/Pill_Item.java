package com.example.medicare.Model;

import android.widget.ImageView;

public class Pill_Item {

    private String namePill;
    private String timeToTake;
    private String countToTake;
    private ImageView imgPill;

    //private int imgPill;

    //private String infoPill


    public Pill_Item(String namePill, String timeToTake,String countToTake) {
        this.namePill = namePill;
        this.countToTake = countToTake;
        this.timeToTake = timeToTake;
        //this.imgPill = imgPill;
    }

    public ImageView getImgPill() {
        return imgPill;
    }

    public Pill_Item setImgPill(ImageView imgPill) {
        this.imgPill = imgPill;
        return this;
    }

    public String getNamePill() {
        return namePill;
    }

    public Pill_Item setNamePill(String namePill) {
        this.namePill = namePill;
        return this;
    }

    public String getCountToTake() {
        return countToTake;
    }

    public Pill_Item setCountToTake(String countToTake) {
        this.countToTake = countToTake;
        return this;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public Pill_Item setTimeToTake(String timeToTake) {
        this.timeToTake = timeToTake;
        return this;
    }
}

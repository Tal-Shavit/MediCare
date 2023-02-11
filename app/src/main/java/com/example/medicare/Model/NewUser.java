package com.example.medicare.Model;

import android.widget.ImageView;

public class NewUser {

    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";

    private ImageView imageView;

    public NewUser() {

    }

    public String getFirstName() {
        return firstName;
    }

    public NewUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public NewUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NewUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public NewUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public NewUser setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }
}

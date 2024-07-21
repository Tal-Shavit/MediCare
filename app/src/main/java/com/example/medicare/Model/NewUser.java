package com.example.medicare.Model;

import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewUser {

    private String name = "";
    private String email = "";
    private ImageView imageView;
    private int img;
    private String colorSystem = "";
    private int count = 0;

    public NewUser() {
    }

    public int getCount() {
        return count;
    }

    public NewUser setCount(int count) {
        this.count = count;
        return this;
    }

    public String getColorSystem() {
        return colorSystem;
    }

    public NewUser setColorSystem(String colorSystem) {
        this.colorSystem = colorSystem;
        return this;
    }

    public int getImg() {
        return img;
    }

    public NewUser setImg(int img) {
        this.img = img;
        return this;
    }

    private ArrayList<PillItem> allPillItems = new ArrayList<>();

    public String getName() {
        return name;
    }

    public NewUser setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<PillItem> getAllPillItems() {
        return allPillItems;
    }

    public NewUser setAllPillItems(ArrayList<PillItem> allPillItems) {
        this.allPillItems = allPillItems;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NewUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public NewUser setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public void addPill(PillItem pill_item) {
        allPillItems.add(pill_item);
    }

    public void loadToDataBase() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Users");
        ref.child(userId).setValue(this);
    }
}

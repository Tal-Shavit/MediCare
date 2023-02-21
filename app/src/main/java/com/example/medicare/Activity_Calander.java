package com.example.medicare;

import static android.text.TextUtils.split;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.medicare.Model.NewUser;
import com.example.medicare.Model.Pill_Item;
import com.example.medicare.Recycler.GridAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Activity_Calander extends AppCompatActivity {

    private TextView calander_LBL_name;
    private TextView calander_LBL_day;
    private TextView calander_LBL_intDayMonth;
    private ImageButton female_IMG_image;
    private Button ActivityCalan_BTN_addPill;
    private MaterialButton calander_MBTN_sunday;
    private MaterialButton calander_MBTN_monday;
    private MaterialButton calander_MBTN_tuesday;
    private MaterialButton calander_MBTN_wednesday;
    private MaterialButton calander_MBTN_thursday;
    private MaterialButton calander_MBTN_friday;
    private MaterialButton calander_MBTN_saturday;
    private TextView calander_TXT_day;

    private GridAdapter gridAdapter;
    private GridView gridView;

    private LinearLayout upLinear;

    private SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    private Date d = new Date();
    private Calendar calendar;
    private String dayName = "";
    private int dayInt;
    private int month;
    private int dayOfWeekNum;

    private ImageButton Dialog_IMGB_chatacter;
    private Spinner dialog_SPR_color;
    private Button dialog_BTN_confirm;
    private Button dialog_BTN_logout;

    private NewUser newUser;
    private ArrayList<Pill_Item>[] allPillItems2;

    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);

        dayName = sdf.format(d);
        calendar = Calendar.getInstance();
        dayInt = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH)+1;
        dayOfWeekNum = calendar.get(Calendar.DAY_OF_WEEK);

        initLoadData();
        findViews();
        initViews();
    }

    private void initLoadData() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(userID).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                newUser = task.getResult().getValue(NewUser.class);
                female_IMG_image.setImageResource(newUser.getImg());
                changeColorSystem(newUser.getColorSystem());
                calander_TXT_day.setText(dayName.toUpperCase());
                if(dayOfWeekNum==1){
                    displayPillPerDaySunday();
                }
                if(dayOfWeekNum==2){
                    displayPillPerDayMonday();
                }
                if(dayOfWeekNum==3){
                    displayPillPerDayTueday();
                }
                if(dayOfWeekNum==4){
                    displayPillPerDayWednesday();
                }
                if(dayOfWeekNum==5){
                    displayPillPerDayThurday();
                }
                if(dayOfWeekNum==6){
                    displayPillPerDayFriday();
                }
                if(dayOfWeekNum==7){
                    displayPillPerDaySaturday();
                }
            }
        });
    }

    private void loadData() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(userID).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                newUser = task.getResult().getValue(NewUser.class);
                female_IMG_image.setImageResource(newUser.getImg());
            }
        });
    }

    private void initViews() {
        calander_LBL_day.setText(dayName);
        calander_LBL_intDayMonth.setText(dayInt+"."+month);
        calander_LBL_name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        ActivityCalan_BTN_addPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewPillScreen();
                loadData();
            }
        });

        female_IMG_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        calander_MBTN_sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("SUNDAY");
                displayPillPerDaySunday();
            }
        });

        calander_MBTN_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("MONDAY");
                displayPillPerDayMonday();
            }
        });
        calander_MBTN_tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("TUESDAY");
                displayPillPerDayTueday();
            }
        });

        calander_MBTN_wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("WEDNESDAY");
                displayPillPerDayWednesday();
            }
        });

        calander_MBTN_thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("THURSDAY");
                displayPillPerDayThurday();
            }
        });

        calander_MBTN_friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("FRIDAY");
                displayPillPerDayFriday();
            }
        });

        calander_MBTN_saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calander_TXT_day.setText("SATURDAY");
                displayPillPerDaySaturday();
            }
        });
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_setting);

        dialog_BTN_logout = dialog.findViewById(R.id.dialog_BTN_logout);
        dialog_SPR_color = dialog.findViewById(R.id.dialog_SPR_color);
        dialog_BTN_confirm = dialog.findViewById(R.id.dialog_BTN_confirm);
        Dialog_IMGB_chatacter = dialog.findViewById(R.id.Dialog_IMGB_chatacter);

        dialog.show();
        Dialog_IMGB_chatacter.setImageResource(newUser.getImg());
        Dialog_IMGB_chatacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCharacterScreen();
            }
        });

        initSpinner();

        dialog_BTN_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.child(userID).get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        newUser = task.getResult().getValue(NewUser.class);
                        newUser.setColorSystem(color);
                        newUser.loadToDataBase();
                    }
                });

                changeColorSystem(color);
                dialog.dismiss();
            }
        });

        dialog_BTN_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                openStartScreen();
            }
        });
    }

    public void changeColorSystem(String color){
        if(color.equals("Green")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightGreen));
        }
        if(color.equals("Blue")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        if(color.equals("Pink")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightPink));
        }
        if(color.equals("Orange")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightOrange));
        }
        if(color.equals("Yellow")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightYellow));
        }
        if(color.equals("Red")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightRed));
        }
    }

    private void initSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colorsList,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dialog_SPR_color.setAdapter(adapter);
        dialog_SPR_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                color = dialog_SPR_color.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void openCharacterScreen() {
        Intent myIntent = new Intent(this, ActivityFemale.class);
        startActivity(myIntent);
    }

    private void openStartScreen() {
        Intent myIntent = new Intent(this, Activity_Start.class);
        startActivity(myIntent);
        finish();
    }

    private void openNewPillScreen() {
        Intent myIntent = new Intent(this, Activity_AddPill.class);
        startActivity(myIntent);
    }

    public void displayPillPerDaySunday(){
        ArrayList<Pill_Item> allPillItems = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getSunday()){
                allPillItems.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItems);
    }

    public void displayPillPerDayMonday(){
        ArrayList<Pill_Item> allPillItemsMon = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getMonday()){
                allPillItemsMon.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItemsMon);
    }

    public void displayPillPerDayTueday(){
        ArrayList<Pill_Item> allPillItems = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getTuesday()){
                allPillItems.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItems);
    }

    public void displayPillPerDayWednesday(){
        ArrayList<Pill_Item> allPillItems = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getWednesday()){
                allPillItems.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItems);
    }

    public void displayPillPerDayThurday(){
        ArrayList<Pill_Item> allPillItems = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getThursday()){
                allPillItems.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItems);
    }

    public void displayPillPerDayFriday(){
        ArrayList<Pill_Item> allPillItems = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getFriday()){
                allPillItems.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItems);
    }

    public void displayPillPerDaySaturday(){
        ArrayList<Pill_Item> allPillItems = new ArrayList<>();
        for (int i = 0; i < newUser.getAllPillItems().size(); i++) {
            if(newUser.getAllPillItems().get(i).getSaturday()){
                allPillItems.add(newUser.getAllPillItems().get(i));
            }
        }
        splitArrayByHours(allPillItems);
    }

    public ArrayList<Pill_Item>[] splitArrayByHours(ArrayList<Pill_Item> pill_itemsDay){
        allPillItems2 = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            allPillItems2[i] = new ArrayList<Pill_Item>();
        }
        for (int i = 0; i < pill_itemsDay.size(); i++) {
            if(LocalTime.parse(pill_itemsDay.get(i).getTimeToTake()).isBefore(LocalTime.of(4,00))){
                allPillItems2[5].add(pill_itemsDay.get(i));//NIGHT
                continue;
            }
            if(LocalTime.parse(pill_itemsDay.get(i).getTimeToTake()).isBefore(LocalTime.of(7,00))){
                allPillItems2[0].add(pill_itemsDay.get(i));//Early morning
                continue;
            }
            if(LocalTime.parse(pill_itemsDay.get(i).getTimeToTake()).isBefore(LocalTime.of(12,00))){
                allPillItems2[1].add(pill_itemsDay.get(i));//Morning
                continue;
            }
            if(LocalTime.parse(pill_itemsDay.get(i).getTimeToTake()).isBefore(LocalTime.of(16,00))){
                allPillItems2[2].add(pill_itemsDay.get(i));//noon
                continue;
            }
            if(LocalTime.parse(pill_itemsDay.get(i).getTimeToTake()).isBefore(LocalTime.of(19,00))){
                allPillItems2[3].add(pill_itemsDay.get(i));//after noon
                continue;
            }
            allPillItems2[4].add(pill_itemsDay.get(i));//Evening
        }

        int[] images = new int[]{
                R.drawable.sunrise,
                R.drawable.sun,
                R.drawable.sky,
                R.drawable.sunset,
                R.drawable.cloudy,
                R.drawable.night
        };

        String[] timeInDay = new String[]{
                "EARLY MORNING",//04:00-06:45
                "MORNING",//07:00-11:45
                "NOON",//12:00-15:45
                "AFTER NOON",//16:00-18:45
                "EVENING",//19:00-23:45
                "NIGHT"//00:00-03:45
        };

        gridAdapter = new GridAdapter(Activity_Calander.this, timeInDay, images, allPillItems2);
        gridView.setAdapter(gridAdapter);
        return allPillItems2;
    }


    private void findViews() {
        calander_LBL_name = findViewById(R.id.calander_LBL_name);
        calander_LBL_day = findViewById(R.id.calander_LBL_day);
        calander_LBL_intDayMonth = findViewById(R.id.calander_LBL_intDayMonth);
        female_IMG_image = findViewById(R.id.female_IMG_image);
        ActivityCalan_BTN_addPill = findViewById(R.id.ActivityCalan_BTN_addPill);
        upLinear = findViewById(R.id.upLinear);
        gridView = findViewById(R.id.grid_view);
        calander_MBTN_sunday = findViewById(R.id.calander_MBTN_sunday);
        calander_MBTN_monday = findViewById(R.id.calander_MBTN_monday);
        calander_MBTN_tuesday = findViewById(R.id.calander_MBTN_tueday);
        calander_MBTN_wednesday = findViewById(R.id.calander_MBTN_wedensday);
        calander_MBTN_thursday = findViewById(R.id.calander_MBTN_thursday);
        calander_MBTN_friday = findViewById(R.id.calander_MBTN_friday);
        calander_MBTN_saturday = findViewById(R.id.calander_MBTN_saturday);
        calander_TXT_day = findViewById(R.id.calander_TXT_day);
    }
}
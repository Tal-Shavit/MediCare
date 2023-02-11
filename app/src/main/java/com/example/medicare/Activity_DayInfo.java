package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.medicare.Recycler.GridAdapter;

public class Activity_DayInfo extends AppCompatActivity {

    public static String KEY_IMG_NAME = "KEY_IMG_NAME";

    private GridAdapter gridAdapter;
    private GridView gridView;

    private int resourceID;
    private ImageView female_IMG_image;

    //private ArrayList<Pill_Item> pillItemsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_info);

        resourceID = getIntent().getIntExtra(KEY_IMG_NAME,0);

        int[] images = new int[]{
                R.drawable.sunrise,
                R.drawable.sun,
                R.drawable.sky,
                R.drawable.sunset,
                R.drawable.cloudy,
                R.drawable.night
        };

        String[] timeInDay = new String[]{
                "EARLY MORNING",
                "MORNING",
                "NOON",
                "AFTER NOON",
                "EVENING",
                "NIGHT"
        };

        findViews();
        initViews();


        //pillItemsArrayList.get(0).setImgPill(R.drawable.other7);
        //pillItemsArrayList.get(1).setImgPill(R.drawable.other8);
        //pillItemsArrayList.get(2).setImgPill(R.drawable.other9);

        gridAdapter = new GridAdapter(Activity_DayInfo.this, timeInDay, images);
        gridView.setAdapter(gridAdapter);
    }

    private void findViews() {
        female_IMG_image = findViewById(R.id.female_IMG_image);
        gridView = findViewById(R.id.grid_view);
        female_IMG_image.setImageResource(resourceID);
    }

    private void initViews() {
        //open dialog if onclick
    }
}
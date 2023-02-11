package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Collections;

public class Activity_Female extends AppCompatActivity {

    private LinearLayout panel_col;
    private LinearLayout.LayoutParams linearParam = null;
    private ArrayList<LinearLayout> arrOfLayout;

    private int[] drawableFemale = new int[]{
            R.drawable.profile,
            R.drawable.vip,
            R.drawable.moslemwoman,
            R.drawable.girl,
            R.drawable.girl2,
            R.drawable.fashionblogger,
            R.drawable.manager,
            R.drawable.grandmother,
            R.drawable.user,
            R.drawable.user6,
            R.drawable.user7,
            R.drawable.user8,
            R.drawable.user9,
            R.drawable.user10,
            R.drawable.user11
    };

    private ImageButton[] imageButtons = new ImageButton[drawableFemale.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female);

        arrOfLayout = new ArrayList(3);

        findViews();
        insertImageView();
        initViews();
    }


    private void findViews() {
        panel_col = findViewById(R.id.panel_col);
    }

    private void openLoginScreen(int imgID) {
        Intent myIntent = new Intent(this, Activity_newUser.class);
        myIntent.putExtra(Activity_newUser.KEY_IMG_NAME, imgID);
        startActivity(myIntent);
        finish();
    }

    public void insertImageView() {
        for (int i = 0; i < 5; i++) {//rows
            LinearLayout linearL = new LinearLayout(Activity_Female.this);
            linearL.setGravity(Gravity.CENTER);
            linearL.setOrientation(LinearLayout.HORIZONTAL);
            linearL.setId(i + 1);
            arrOfLayout.add(linearL);

            linearParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            insertObject(i, linearL);
            arrOfLayout.add(linearL);
            panel_col.addView(linearL, linearParam);
        }
    }

    public void insertObject(int i, LinearLayout linearL) {
        for (int j = 0; j < 3; j++) {//cols
            ImageButton image = new ImageButton(Activity_Female.this);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setBackgroundColor(getResources().getColor(R.color.white));
            image.setId(View.generateViewId());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(350, 350);
            image.setImageResource(drawableFemale[i * 3 + j]);
            imageButtons[i * 3 + j] = image;
            linearL.addView(image, lp);
        }
    }

        private void initViews() {
        for (int i = 0; i < imageButtons.length; i++) {
            int finalI = i;
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openLoginScreen(drawableFemale[finalI]);
                }
            });
        }
    }
}

  /*private ImageButton female_IBT_profile;
    private ImageButton female_IBT_vip;
    private ImageButton female_IBT_moslemwoman;
    private ImageButton female_IBT_girl;
    private ImageButton female_IBT_girl2;
    private ImageButton female_IBT_fashionblogger;
    private ImageButton female_IBT_manager;
    private ImageButton female_IBT_grandmother;
    private ImageButton female_IBT_user;
    private ImageButton female_IBT_user6;
    private ImageButton female_IBT_user7;
    private ImageButton female_IBT_user8;
    private ImageButton female_IBT_user9;
    private ImageButton female_IBT_user10;
    private ImageButton female_IBT_user11;*/


        /*female_IBT_fashionblogger = findViewById(R.id.female_IBT_fashionblogger);
        female_IBT_profile = findViewById(R.id.female_IBT_profile);
        female_IBT_vip = findViewById(R.id.female_IBT_vip);
        female_IBT_moslemwoman = findViewById(R.id.female_IBT_moslemwoman);
        female_IBT_girl = findViewById(R.id.female_IBT_girl);
        female_IBT_girl2 = findViewById(R.id.female_IBT_girl2);
        female_IBT_manager = findViewById(R.id.female_IBT_manager);
        female_IBT_grandmother = findViewById(R.id.female_IBT_grandmother);
        female_IBT_user = findViewById(R.id.female_IBT_user);*/

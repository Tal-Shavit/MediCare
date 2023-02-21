package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Activity_Other extends AppCompatActivity {

    private LinearLayout panel_col;
    private LinearLayout.LayoutParams linearParam = null;
    private ArrayList<LinearLayout> arrOfLayout;

    private int[] drawableOthers = new int[]{
            R.drawable.other1,
            R.drawable.other2,
            R.drawable.other3,
            R.drawable.other4,
            R.drawable.other5,
            R.drawable.other12,
            R.drawable.other11,
            R.drawable.other13,
            R.drawable.other10,
            R.drawable.other20,
            R.drawable.other21,
            R.drawable.other22,
            R.drawable.other23,
            R.drawable.other24,
            R.drawable.other19,
            R.drawable.other6,
            R.drawable.other14,
            R.drawable.other15,
            R.drawable.other16,
            R.drawable.other17,
            R.drawable.other18,
            R.drawable.other7,
            R.drawable.other8,
            R.drawable.other9
    };

    private ImageButton[] imageButtons = new ImageButton[drawableOthers.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        arrOfLayout = new ArrayList(3);

        findViews();
        insertImageView();
        initViews();
    }

    private void findViews() {
        panel_col = findViewById(R.id.panel_col);
    };

    /*private void openLoginScreen(int imgID) {
        Intent myIntent = new Intent(this, Activity_newUser.class);
        myIntent.putExtra(Activity_newUser.KEY_IMG_NAME,imgID);
        startActivity(myIntent);
        finish();
    }*/

    public void insertImageView() {
        for (int i = 0; i < (drawableOthers.length)/3; i++) {//rows
            LinearLayout linearL = new LinearLayout(Activity_Other.this);
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
            ImageButton image = new ImageButton(Activity_Other.this);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setBackgroundColor(getResources().getColor(R.color.white));
            image.setId(View.generateViewId());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(350, 350);
            image.setImageResource(drawableOthers[i * 3 + j]);
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
                    //openLoginScreen(drawableOthers[finalI]);
                }
            });
        }
    }
}
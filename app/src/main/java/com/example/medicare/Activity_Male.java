package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Activity_Male extends AppCompatActivity {

    private LinearLayout panel_col;
    private LinearLayout.LayoutParams linearParam = null;
    private ArrayList<LinearLayout> arrOfLayout;

    private int[] drawableMale = new int[]{
            R.drawable.grandpa,
            R.drawable.man,
            R.drawable.boy,
            R.drawable.man1,
            R.drawable.man2,
            R.drawable.model,
            R.drawable.employee,
            R.drawable.man4,
            R.drawable.man3,
            R.drawable.boy2,
            R.drawable.boy3,
            R.drawable.man5,
            R.drawable.man6,
            R.drawable.man7,
            R.drawable.man8
    };

    private ImageButton[] imageButtons = new ImageButton[drawableMale.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male);

        arrOfLayout = new ArrayList(3);

        findViews();
        insertImageView();
        initViews();
    }

    private void findViews() {
        panel_col = findViewById(R.id.panel_col);
    };

    private void openLoginScreen(int imgID) {
        Intent myIntent = new Intent(this, Activity_newUser.class);
        myIntent.putExtra(Activity_newUser.KEY_IMG_NAME,imgID);
        startActivity(myIntent);
        finish();
    }

    public void insertImageView() {
        for (int i = 0; i < (drawableMale.length)/3; i++) {//rows
            LinearLayout linearL = new LinearLayout(Activity_Male.this);
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
            ImageButton image = new ImageButton(Activity_Male.this);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setBackgroundColor(getResources().getColor(R.color.white));
            image.setId(View.generateViewId());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(350, 350);
            image.setImageResource(drawableMale[i * 3 + j]);
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
                    openLoginScreen(drawableMale[finalI]);
                }
            });
        }
    }
}

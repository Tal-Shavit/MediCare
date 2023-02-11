package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medicare.Fragment.Fragment_Data1;
import com.example.medicare.Model.NewUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Activity_Calander extends AppCompatActivity {

    public static String KEY_IMG_NAME = "KEY_IMG_NAME";
    public static String KEY_COLOR = "KEY_COLOR";

    private TextView calander_LBL_name;
    private TextView calander_LBL_day;
    private TextView calander_LBL_intDayMonth;
    private ImageView female_IMG_image;

    private TextView calander_LBL_sunday;

    private Button ActivityCalan_BTN_check;
    private Button ActivityCalan_BTN_addPill;

    private Fragment_Data1 fragment_data1;

    private LinearLayout upLinear;

    private int resourceID;
    private int idColor;

    private NewUser newUser;

    private SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    private Date d = new Date();
    private Calendar calendar;
    private String dayName;
    private int dayInt;
    private int month;

    //private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);


        dayName = sdf.format(d);
        calendar = Calendar.getInstance();
        dayInt = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH)+1;

        //String allName = newUser.getFirstName();
        //calander_LBL_name.setText(allName);


        resourceID = getIntent().getIntExtra(KEY_IMG_NAME,0);
        //color = getIntent().getStringExtra(KEY_COLOR);
        idColor = getIntent().getIntExtra(KEY_COLOR,0);
        //Log.d("LALA",":"+idColor);


        findViews();
        initFragment();

        calander_LBL_day.setText(dayName);
        calander_LBL_intDayMonth.setText(dayInt+"."+month);


        //upLinear.setBackground(getResources().getDrawable(idColor));


        /*if(color == "Green"){
            upLinear.setBackgroundColor(Color.parseColor("#89C7CF"));
        }
        if(color == "Blue")
            upLinear.setBackground(getResources().getDrawable(R.color.lightPink));
        if(color == "Pink")
            upLinear.setBackground(getResources().getDrawable(R.color.lightPink));
        if(color == "Orange")
            upLinear.setBackground(getResources().getDrawable(R.color.lightOrange));
        if(color == "Yellow")
            upLinear.setBackground(getResources().getDrawable(R.color.lightYellow));
        if(color == "Red")
            upLinear.setBackground(getResources().getDrawable(R.color.lightRed));
*/
        initViews(resourceID,idColor);
    }

    private void initFragment() {
        fragment_data1 = Fragment_Data1.newInstance();
        //fragment_data1.setCallBack_location(callBack_location);
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.add(R.id.calander_LAY_cyrcle, fragment_data1);
        transaction1.commit();
    }

    private void initViews(int imgID, int color) {
        female_IMG_image.setImageResource(imgID);

        ActivityCalan_BTN_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDayScreen(imgID);
            }
        });

        ActivityCalan_BTN_addPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewPillScreen();
            }
        });
    }

    private void openDayScreen(int imgID) {
        Intent myIntent = new Intent(this, Activity_DayInfo.class);
        myIntent.putExtra(Activity_Calander.KEY_IMG_NAME,imgID);
        startActivity(myIntent);
    }

    private void openNewPillScreen() {
        Intent myIntent = new Intent(this, Activity_AddPill.class);
        startActivity(myIntent);
    }

    private void findViews() {
        calander_LBL_name = findViewById(R.id.calander_LBL_name);
        calander_LBL_day = findViewById(R.id.calander_LBL_day);
        calander_LBL_intDayMonth = findViewById(R.id.calander_LBL_intDayMonth);
        //calander_LBL_sunday = findViewById(R.id.calander_LBL_sunday);
        female_IMG_image = findViewById(R.id.female_IMG_image);
        ActivityCalan_BTN_check = findViewById(R.id.ActivityCalan_BTN_check);
        ActivityCalan_BTN_addPill = findViewById(R.id.ActivityCalan_BTN_addPill);
        upLinear = findViewById(R.id.upLinear);
    }


}
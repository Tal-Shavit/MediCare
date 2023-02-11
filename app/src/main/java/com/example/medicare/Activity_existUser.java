package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_existUser extends AppCompatActivity {

    private Button newUser_BTN_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist_user);

        findViews();
        initViews();


    }


    private void initViews() {
        newUser_BTN_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeeklyCalender();
            }
        });

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Activity_existUser.this, android.R.layout.simple_spinner_item, colors);


    }

    private void openWeeklyCalender() {
        Intent myIntent = new Intent(this, Activity_Calander.class);
        startActivity(myIntent);
        finish();
    }

    private void findViews() {
        newUser_BTN_confirm = findViewById(R.id.newUser_BTN_confirm);

    }

}
package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class Activity_Start extends AppCompatActivity {

    private Button ActivityStart_BTN_newUser;
    private Button ActivityStart_BTN_existUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViews();
        initViews();
    }

    public void initViews() {
        ActivityStart_BTN_newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreenNewUser();
            }
        });

        ActivityStart_BTN_existUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreenExistUser();
            }
        });
    }

    public void openScreenNewUser() {
            Intent myIntent = new Intent(this, Activity_newUser.class);
            startActivity(myIntent);
            finish();
    }

    public void openScreenExistUser() {
        Intent myIntent = new Intent(this, Activity_existUser.class);
        startActivity(myIntent);
        finish();
    }

    public void findViews() {
        ActivityStart_BTN_newUser = findViewById(R.id.ActivityStart_BTN_newUser);
        ActivityStart_BTN_existUser = findViewById(R.id.ActivityStart_BTN_existUser);
    }
}
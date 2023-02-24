package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLottie extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        findViews();
        lottieAnimationView.resumeAnimation();
        new Handler().postDelayed(() -> {
            openStartScreen();
        },6000);
    }

    public void openStartScreen() {
        Intent intent = new Intent(this, ActivityStart.class);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        lottieAnimationView = findViewById(R.id.lottie_ANIM_lottie);
    }

}


package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.medicare.Model.NewUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivitySplash extends AppCompatActivity {

    private ImageView activitySplash_IMG_drug;
    private RelativeLayout upLinear;
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    private NewUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initLoadData();
        findViews();

        activitySplash_IMG_drug.setVisibility(View.INVISIBLE);

        startAnimation(activitySplash_IMG_drug);
    }

    private void startAnimation(View view) {
        view.setVisibility(View.VISIBLE);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;

        //starting state:
        view.setY(-height/8); // top-middle of the screen
        view.setScaleX(0.0f); //no size X
        view.setScaleY(0.0f); // no size Y
        view.setAlpha(0.0f); // Transparent

        view.animate()
                .alpha(1.0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .translationY(0)
                .setDuration(4000) //in miliseconds
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        openStartScreen();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }


    private void initLoadData() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();//"Users"
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Users").child(userID).exists()) {
                    databaseReference.child("Users").child(userID).get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            newUser = task.getResult().getValue(NewUser.class);
                            changeColorSystem(newUser.getColorSystem());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void changeColorSystem(String color) {
        if (color.equals("Green")) {
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightGreen));
        }
        if (color.equals("Blue")) {
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        if (color.equals("Pink")) {
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightPink));
        }
        if (color.equals("Orange")) {
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightOrange));
        }
        if (color.equals("Yellow")) {
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightYellow));
        }
        if (color.equals("Red")) {
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightRed));
        }
    }

    public void openStartScreen() {
        Intent intent = new Intent(this, Activity_Start.class);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        activitySplash_IMG_drug = findViewById(R.id.activitySplash_IMG_drug);
        upLinear = findViewById(R.id.upLinear);
    }

}
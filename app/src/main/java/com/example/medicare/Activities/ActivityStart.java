package com.example.medicare.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.medicare.Model.NewUser;
import com.example.medicare.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class ActivityStart extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        if (user == null){
            login();
        }else{
            String uid = user.getUid();
            String name = user.getDisplayName();
            String email = user.getEmail();
            openWeeklyCalender();
        }
    }

    private final ActivityResultLauncher<Intent> signInLauncher = (ActivityResultLauncher<Intent>) registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        //check is user in real time
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(!snapshot.child(mAuth.getCurrentUser().getUid()).exists()){
                   NewUser newUser = new NewUser();
                   newUser.setName(mAuth.getCurrentUser().getDisplayName())
                           .setEmail(mAuth.getCurrentUser().getEmail())
                           .setImg(R.drawable.other2)
                           .setColorSystem("Green").setCount(0);
                   newUser.loadToDataBase();
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        openWeeklyCalender();
    }

    private void login() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());


        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void openWeeklyCalender() {//int imgID, int color
        Intent myIntent = new Intent(this, ActivityCalander.class);
        startActivity(myIntent);
        finish();
    }

}
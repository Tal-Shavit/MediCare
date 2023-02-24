package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.medicare.Model.NewUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityCharacters extends AppCompatActivity {

    private LinearLayout panel_col;
    private LinearLayout.LayoutParams linearParam = null;
    private ArrayList<LinearLayout> arrOfLayout;
    private NewUser newUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private int[] drawableFemale = new int[]{
            R.drawable.profile,R.drawable.vip,R.drawable.moslemwoman,R.drawable.girl,
            R.drawable.girl2, R.drawable.fashionblogger,R.drawable.manager,
            R.drawable.grandmother,R.drawable.user, R.drawable.user6,R.drawable.user7,
            R.drawable.user8,R.drawable.user9,R.drawable.user10,R.drawable.user11,
            R.drawable.grandpa,R.drawable.man,R.drawable.boy,R.drawable.man1,
            R.drawable.man2,R.drawable.model,R.drawable.employee,R.drawable.man4,R.drawable.man3,
            R.drawable.boy2,R.drawable.boy3,R.drawable.man5,R.drawable.man6,R.drawable.man7,
            R.drawable.man8,R.drawable.other1,R.drawable.other2,R.drawable.other3,R.drawable.other4,
            R.drawable.other5,R.drawable.other12,R.drawable.other11,R.drawable.other13,
            R.drawable.other10,R.drawable.other20,R.drawable.other21,R.drawable.other22,
            R.drawable.other23,R.drawable.other24,R.drawable.other19, R.drawable.other6,
            R.drawable.other14, R.drawable.other15,R.drawable.other16,R.drawable.other17,
            R.drawable.other18,R.drawable.other7,R.drawable.other8,R.drawable.other9
    };

    private ImageButton[] imageButtons = new ImageButton[drawableFemale.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        arrOfLayout = new ArrayList(3);

        findViews();
        insertImageView();
        initViews();
    }


    private void findViews() {
        panel_col = findViewById(R.id.panel_col);
    }


    public void insertImageView() {
        for (int i = 0; i < 18; i++) {//rows
            LinearLayout linearL = new LinearLayout(ActivityCharacters.this);
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
            ImageButton image = new ImageButton(ActivityCharacters.this);
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
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child("Users").child(userID).exists()){
                            databaseReference.child("Users").child(userID).get().addOnCompleteListener(task -> {
                                if(task.isSuccessful()){
                                    newUser = task.getResult().getValue(NewUser.class);
                                    newUser.setImg(drawableFemale[finalI]);
                                    newUser.loadToDataBase();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                finish();
                }
             });
        }

    }
}

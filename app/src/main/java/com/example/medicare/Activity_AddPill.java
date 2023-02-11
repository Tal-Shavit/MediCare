package com.example.medicare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.medicare.Model.NewUser;

public class Activity_AddPill extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button newPill_BTN_confirm;
    private ImageButton pill_IBT_Camera;
    private ImageButton pill_IBT_Gallery;
    private ImageView addPill_IMG_image;
    private Button backButton;
    private Button againButton;

    private Spinner spinnerForHour;


    private NewUser newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        findViews();
        initViews();
    }

    private void initViews() {
        newPill_BTN_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backCalanderScreen();
            }
        });

        pill_IBT_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
                showImageDialog();
            }
        });

        pill_IBT_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
                showImageDialog();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hoursList,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForHour.setAdapter(adapter);
        spinnerForHour.setOnItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            addPill_IMG_image.setImageBitmap(bitmap);
        }
        if(requestCode == 3){
            Uri selectedImage = data.getData();
            addPill_IMG_image.setImageURI(selectedImage);
        }
    }

    private void showImageDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.show_image);

        addPill_IMG_image = dialog.findViewById(R.id.addPill_IMG_image);
        backButton = dialog.findViewById(R.id.backButton);
        againButton = dialog.findViewById(R.id.againButton);

        dialog.show();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add the picture
                //newUser.setImageView(addPill_IMG_image);
                dialog.dismiss();
            }
        });
    }

    private void findViews() {
        newPill_BTN_confirm = findViewById(R.id.newPill_BTN_confirm);
        pill_IBT_Camera = findViewById(R.id.pill_IBT_Camera);
        pill_IBT_Gallery = findViewById(R.id.pill_IBT_Gallery);
        spinnerForHour = findViewById(R.id.spinnerForHour);
    }


    private void backCalanderScreen() {
        Intent myIntent = new Intent(this, Activity_Calander.class);
        startActivity(myIntent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String time = adapterView.getItemAtPosition(position).toString();
        Log.d("LALA",time);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
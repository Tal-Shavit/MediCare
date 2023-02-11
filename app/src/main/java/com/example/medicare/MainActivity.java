package com.example.medicare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private ImageView Main_IMG_image;
    private MaterialButton Main_BTN_Camera;
    private MaterialButton Main_BTN_Gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();


        //Request for camera runtime permission
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA}, 100);
        }

        initViews();

    }

    private void findViews() {
        Main_IMG_image = findViewById(R.id.Main_IMG_image);
        Main_BTN_Camera = findViewById(R.id.Main_BTN_Camera);
        Main_BTN_Gallery = findViewById(R.id.Main_BTN_Gallery);
    }

    private void initViews() {

        Main_BTN_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        Main_BTN_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            Main_IMG_image.setImageBitmap(bitmap);
        }
        if(requestCode == 3){
           Uri selectedImage = data.getData();
           Main_IMG_image.setImageURI(selectedImage);
        }

    }
}
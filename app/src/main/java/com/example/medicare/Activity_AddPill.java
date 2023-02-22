package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.Model.NewUser;
import com.example.medicare.Model.Pill_Item;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity_AddPill extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //private static final int ImageBack = 1;
    private StorageReference storageReference;

    private Button newPill_BTN_confirm;
    private ImageButton pill_IBT_Camera;
    private ImageButton pill_IBT_Gallery;
    private ImageView addPill_IMG_image;
    private Button backButton;
    private Button againButton;
    private EditText addpill_TXT_count;

    private CheckBox checkboxSun;
    private CheckBox checkboxMon;
    private CheckBox checkboxTue;
    private CheckBox checkboxWed;
    private CheckBox checkboxThu;
    private CheckBox checkboxFri;
    private CheckBox checkboxSat;
    private TextView addPill_LBL_search;
   // private Spinner spinnerForPill;
    private Spinner spinnerForHour;
    private LinearLayout upLinear;
    private NewUser newUser;
    private Pill_Item pill_item;
    private Uri selectedImage;
    private int countOfPills;
    private ArrayList<String> arrayListAllPill;
    private Dialog dialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferenceDrugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        //Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
        loadData();
        initArrayListDrugs();
        findViews();
        initViews();
    }

    private void loadData(){
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();//"Users"
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(userID).exists()){
                    databaseReference.child("Users").child(userID).get().addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
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

    private void initViews() {
        addPill_LBL_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Activity_AddPill.this);
                dialog.setContentView(R.layout.dialog_searchig_bar);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();

                EditText searchBar_TXT_serch = dialog.findViewById(R.id.searchBar_TXT_serch);
                ListView searchBar_LISTV_listView = dialog.findViewById(R.id.searchBar_LISTV_listView);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Activity_AddPill.this, android.R.layout.simple_list_item_1, arrayListAllPill);
                searchBar_LISTV_listView.setAdapter(arrayAdapter);
                searchBar_TXT_serch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        arrayAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                searchBar_LISTV_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        addPill_LBL_search.setText(arrayAdapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });

        newPill_BTN_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.child(userID).get().addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       newUser = task.getResult().getValue(NewUser.class);
                       if(addpill_TXT_count.getText().toString().equals("")){
                           addpill_TXT_count.setHint("FILL COUNT!");
                       }
                       else{
                           countOfPills = Integer.parseInt(addpill_TXT_count.getText()+"");
                       }
                       pill_item = new Pill_Item();//spinnerForPill.getSelectedItem()+
                       pill_item.setNamePill("")
                               .setCountToTake(countOfPills)
                               .setTimeToTake(pill_item.convertStringToTime(spinnerForHour.getSelectedItem().toString()).toString())
                               .setSunday(checkboxSun.isChecked())
                               .setMonday(checkboxMon.isChecked()).setTuesday(checkboxTue.isChecked())
                               .setWednesday(checkboxWed.isChecked()).setThursday(checkboxThu.isChecked())
                               .setFriday(checkboxFri.isChecked()).setSaturday(checkboxSat.isChecked());
                       newUser.addPill(pill_item);
                       newUser.loadToDataBase();
                   }
                });
                if(!addpill_TXT_count.getText().toString().equals("")){
                    backCalanderScreen();
                }
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
                /*Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
                showImageDialog();*/
                /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,3);*/
            }
        });


        initSpinner();
    }

    private void initArrayListDrugs() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceDrugs = firebaseDatabase.getReference("Drugs");
        arrayListAllPill = new ArrayList<>();

        databaseReferenceDrugs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    //arrayListAllPill.clear();
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Pill_Item pill_item1 = dataSnapshot.getValue(Pill_Item.class);
                        //Log.d("LALA", arrayListAllPill.size()+"");
                        arrayListAllPill.add(pill_item1.getNamePill()+"");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                pill_item = snapshot.getValue(Pill_Item.class);
                arrayListAllPill.add(pill_item.getNamePill()+"");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



    }


    private void initSpinner() {
        /*ArrayAdapter<CharSequence> adapterPills = ArrayAdapter.createFromResource(this, R.array.pillsList,android.R.layout.simple_spinner_item);
        adapterPills.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForPill.setAdapter(adapterPills);
        spinnerForPill.setOnItemSelectedListener(this);*/

        ArrayAdapter<CharSequence> adapterHours = ArrayAdapter.createFromResource(this, R.array.hoursList,android.R.layout.simple_spinner_item);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForHour.setAdapter(adapterHours);
        spinnerForHour.setOnItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if(requestCode == 3){
            if(resultCode== RESULT_OK){
                selectedImage = data.getData();
                StorageReference imageName = Folder.child(spinnerForPill.getSelectedItem()+"");
                imageName.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
            }
        }*/

        if(requestCode == 100){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            addPill_IMG_image.setImageBitmap(bitmap);
        }
        /*if(requestCode == 3){
            Uri selectedImage = data.getData();
            addPill_IMG_image.setImageURI(selectedImage);
        }*/
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
        //spinnerForPill = findViewById(R.id.spinnerForPill);
        addPill_LBL_search = findViewById(R.id.addPill_LBL_search);
        spinnerForHour = findViewById(R.id.spinnerForHour);
        addpill_TXT_count = findViewById(R.id.addpill_TXT_count);
        checkboxSun = findViewById(R.id.checkboxSun);
        checkboxMon = findViewById(R.id.checkboxMon);
        checkboxTue = findViewById(R.id.checkboxTue);
        checkboxWed = findViewById(R.id.checkboxWed);
        checkboxThu = findViewById(R.id.checkboxThu);
        checkboxFri = findViewById(R.id.checkboxFri);
        checkboxSat = findViewById(R.id.checkboxSat);
        upLinear = findViewById(R.id.upLinear);
    }


    private void backCalanderScreen() {
        loadData();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //String time = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void changeColorSystem(String color){
        if(color.equals("Green")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightGreen));
        }
        if(color.equals("Blue")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        if(color.equals("Pink")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightPink));
        }
        if(color.equals("Orange")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightOrange));
        }
        if(color.equals("Yellow")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightYellow));
        }
        if(color.equals("Red")){
            upLinear.setBackgroundColor(getResources().getColor(R.color.lightRed));
        }
    }
}
package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.medicare.Model.PillItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityAddPill extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final int REQUEST_CODE_GALLERY = 1;
    private StorageReference storageReference;
    private Button newPill_BTN_confirm;
    private ImageButton pill_IBT_Gallery;
    private ImageView addPill_IMG_image;
    private Button backButton;
    private EditText addpill_TXT_count;
    private CheckBox checkboxSun;
    private CheckBox checkboxMon;
    private CheckBox checkboxTue;
    private CheckBox checkboxWed;
    private CheckBox checkboxThu;
    private CheckBox checkboxFri;
    private CheckBox checkboxSat;
    private TextView addPill_LBL_search;
    private Spinner spinnerForHour;
    private LinearLayout upLinear;
    private NewUser newUser;
    private PillItem pill_item;
    private int countOfPills;
    private ArrayList<String> arrayListAllPill;
    private Dialog dialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferenceDrugs;
    private Uri imagePath;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        storageReference = FirebaseStorage.getInstance().getReference();

        loadData();
        initArrayListDrugs();
        findViews();
        initViews();
    }

    private void loadData(){
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");
        databaseReference.child(userID).get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               newUser = task.getResult().getValue(NewUser.class);
               changeColorSystem(newUser.getColorSystem());
           }
        });
    }

    private void initArrayListDrugs() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceDrugs = firebaseDatabase.getReference("Drugs");
        arrayListAllPill = new ArrayList<>();

        databaseReferenceDrugs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (int i = 8; i <= 20099; i++) {
                        if(snapshot.child(i+"").exists()){
                            for (DataSnapshot dataSnapshot : snapshot.child(i+"").getChildren()){
                                String name = dataSnapshot.getValue().toString();
                                if(name.contains("/")){
                                    name = name.replace("/","-");
                                }
                                arrayListAllPill.add(name);
                            }

                        }
                    }
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
                dialog = new Dialog(ActivityAddPill.this);
                dialog.setContentView(R.layout.dialog_searchig_bar);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();

                EditText searchBar_TXT_serch = dialog.findViewById(R.id.searchBar_TXT_serch);
                ListView searchBar_LISTV_listView = dialog.findViewById(R.id.searchBar_LISTV_listView);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ActivityAddPill.this, android.R.layout.simple_list_item_1, arrayListAllPill);
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
                        addPill_LBL_search.setTextColor(Color.BLACK);
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
                           Toast.makeText(ActivityAddPill.this, "FILL COUNT!", Toast.LENGTH_SHORT).show();
                           addpill_TXT_count.setHint("FILL COUNT!");
                       }
                       if(addPill_LBL_search.getText().toString().isEmpty()){
                           Toast.makeText(ActivityAddPill.this, "SELECT A PILL", Toast.LENGTH_SHORT).show();
                       }
                       if((!checkboxSun.isChecked()&&!checkboxMon.isChecked()&&!checkboxTue.isChecked()&&!checkboxWed.isChecked()&&!checkboxThu.isChecked()&&!checkboxFri.isChecked()&&!checkboxSat.isChecked())){
                           Toast.makeText(ActivityAddPill.this, "CHOOSE AT LEAST 1 DAY", Toast.LENGTH_SHORT).show();
                       }
                       if(spinnerForHour.getSelectedItem().toString().isEmpty()){
                           Toast.makeText(ActivityAddPill.this, "SELECT HOUR!", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           countOfPills = Integer.parseInt(addpill_TXT_count.getText()+"");
                           pill_item = new PillItem();
                           pill_item.setNamePill(addPill_LBL_search.getText().toString())
                                   .setCountToTake(countOfPills)
                                   .setTimeToTake(pill_item.convertStringToTime(spinnerForHour.getSelectedItem().toString()).toString())
                                   .setSunday(checkboxSun.isChecked())
                                   .setMonday(checkboxMon.isChecked()).setTuesday(checkboxTue.isChecked())
                                   .setWednesday(checkboxWed.isChecked()).setThursday(checkboxThu.isChecked())
                                   .setFriday(checkboxFri.isChecked()).setSaturday(checkboxSat.isChecked());
                           newUser.addPill(pill_item);
                           newUser.loadToDataBase();
                       }
                   }
                });
                if(!addpill_TXT_count.getText().toString().equals("") && !addPill_LBL_search.getText().toString().isEmpty() &&
                        (checkboxSun.isChecked()||checkboxMon.isChecked()||checkboxTue.isChecked()||checkboxWed.isChecked()||checkboxThu.isChecked()||checkboxFri.isChecked()||checkboxSat.isChecked()) && (!spinnerForHour.getSelectedItem().toString().isEmpty())){
                    backCalanderScreen();
                }
            }
        });

        pill_IBT_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoInent = new Intent(Intent.ACTION_PICK);
                photoInent.setType("image/");
                startActivityForResult(photoInent,1);
                showImageDialog();
            }
        });
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapterHours = ArrayAdapter.createFromResource(this, R.array.hoursList,android.R.layout.simple_spinner_item);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForHour.setAdapter(adapterHours);
        spinnerForHour.setOnItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            imagePath = data.getData();
            getImageInImageView();
            uploadImageToStorage();
        }
    }

    private void uploadImageToStorage() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        FirebaseStorage.getInstance().getReference("images/"+ addPill_LBL_search.getText().toString()+".jpg").putFile(imagePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ActivityAddPill.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                }
                else{
                }
                progressDialog.dismiss();
            }
        });
    }

    public Bitmap getImageInImageView() {
        bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addPill_IMG_image.setImageBitmap(bitmap);
        return bitmap;
    }

    private void showImageDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.show_image_pill);

        addPill_IMG_image = dialog.findViewById(R.id.addPill_IMG_image);
        backButton = dialog.findViewById(R.id.backButton);

        dialog.show();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void findViews() {
        newPill_BTN_confirm = findViewById(R.id.newPill_BTN_confirm);
        pill_IBT_Gallery = findViewById(R.id.pill_IBT_Gallery);
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
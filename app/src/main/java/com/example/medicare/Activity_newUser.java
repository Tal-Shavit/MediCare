package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicare.Model.NewUser;

public class Activity_newUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static String KEY_IMG_NAME = "KEY_IMG_NAME";

    private Button newUser_BTN_male;
    private Button newUser_BTN_female;
    private Button newUser_BTN_other;
    private Button newUser_BTN_confirm;
    private Spinner newUser_SPR_spinner;

    private EditText newUser_TXT_firstName;
    private EditText newUser_TXT_lastName;
    private EditText newUser_TXT_email;
    private EditText newUser_TXT_passwrd;

    private int resourceID;
    private String color;
    private int idColor;

    private String firstName;
    private String lastName;
    private String email;
    private String passwrd;

    private Toast toast;

    //private ArrayList<NewUser> newUserArrayList;
    public NewUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        resourceID = getIntent().getIntExtra(KEY_IMG_NAME,0);

        findViews();
        initViews(resourceID);

    }

    private void initUser() {
        firstName = newUser_TXT_firstName.getText().toString();
        lastName = newUser_TXT_lastName.getText().toString();
        email = newUser_TXT_email.getText().toString();
        passwrd = newUser_TXT_passwrd.getText().toString();
       // newUser = new NewUser().setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(passwrd);

    }

    private void initViews(int imgID) {
        newUser_BTN_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFemaleScreen();

            }
        });

        newUser_BTN_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMaleScreen();
            }
        });

        newUser_BTN_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOtherScreen();
            }
        });

        newUser_BTN_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initUser();
                if(firstName.isEmpty()){
                    toast = Toast.makeText(Activity_newUser.this, "YOU NEED TO FILL FIRST NAME! ", Toast.LENGTH_SHORT);
                    toast.show();
                    newUser_TXT_firstName.setHint("FILL ME!");
                }
                if(lastName.isEmpty()){
                    toast = Toast.makeText(Activity_newUser.this, "YOU NEED TO FILL LAST NAME! ", Toast.LENGTH_SHORT);
                    toast.show();
                    newUser_TXT_lastName.setHint("FILL ME!");
                }
                if(email.isEmpty()){
                    toast = Toast.makeText(Activity_newUser.this, "YOU NEED TO FILL EMAIL! ", Toast.LENGTH_SHORT);
                    toast.show();
                    newUser_TXT_email.setHint("FILL ME!");
                }
                if(passwrd.isEmpty()){
                    toast = Toast.makeText(Activity_newUser.this, "YOU NEED TO FILL PASSWORD! ", Toast.LENGTH_SHORT);
                    toast.show();
                    newUser_TXT_passwrd.setHint("FILL ME!");
                }
                else{
                    newUser = new NewUser().setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(passwrd);
                    openWeeklyCalender(imgID, idColor);
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colorsList,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newUser_SPR_spinner.setAdapter(adapter);
        newUser_SPR_spinner.setOnItemSelectedListener(this);
    }

    private void openMaleScreen() {
        Intent myIntent = new Intent(this, Activity_Male.class);
        startActivity(myIntent);
        finish();
    }

    private void openFemaleScreen() {
            Intent myIntent = new Intent(this, Activity_Female.class);
            startActivity(myIntent);
            finish();
    }

    private void openOtherScreen() {
        Intent myIntent = new Intent(this, Activity_Other.class);
        startActivity(myIntent);
        finish();
    }

    private void openWeeklyCalender(int imgID, int color) {
        Intent myIntent = new Intent(this, Activity_Calander.class);
        myIntent.putExtra(Activity_Calander.KEY_IMG_NAME,imgID);
        myIntent.putExtra(Activity_Calander.KEY_COLOR,color);
        startActivity(myIntent);
        finish();
    }

    private void findViews() {
        newUser_BTN_male = findViewById(R.id.newUser_BTN_male);
        newUser_BTN_female = findViewById(R.id.newUser_BTN_female);
        newUser_BTN_other = findViewById(R.id.newUser_BTN_other);
        newUser_BTN_confirm = findViewById(R.id.newUser_BTN_confirm);
        newUser_SPR_spinner = findViewById(R.id.newUser_SPR_spinner);

        newUser_TXT_firstName = findViewById(R.id.newUser_TXT_firstName);
        newUser_TXT_lastName = findViewById(R.id.newUser_TXT_lastName);
        newUser_TXT_email = findViewById(R.id.newUser_TXT_email);
        newUser_TXT_passwrd = findViewById(R.id.newUser_TXT_passwrd);
    }
//            upLinear.setBackground(getResources().getDrawable(R.color.lightGreen));
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        color = adapterView.getItemAtPosition(position).toString();
        Log.d("LALA",color);
        if(color == "Green")
            idColor = R.color.lightPink;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
package com.example.bloodbuddy.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bloodbuddy.R;

public class RegisterActivity extends AppCompatActivity {


     private Button reg_btn;
     private EditText user_name,user_email,user_dob,user_address,user_blood_grp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        reg_btn=findViewById(R.id.reg_btn);
        user_name=findViewById(R.id.user_name);
        user_address=findViewById(R.id.user_address);
        user_email=findViewById(R.id.user_email);
        user_dob=findViewById(R.id.user_dob);
        user_blood_grp=findViewById(R.id.user_blood_grp);


        

    }
}
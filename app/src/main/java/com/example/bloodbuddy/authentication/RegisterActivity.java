package com.example.bloodbuddy.authentication;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.bloodbuddy.utils.Dialog;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodbuddy.MainActivity;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {


    private  String phone_number=null;
    private  String name=null;
    private  String email=null;
    private  String dob=null;
    private  String blood_grp=null;
    private  String address = null;




    private FirebaseAuth auth;
    private DatabaseReference reference;
    private FirebaseDatabase db;
    private Button reg_btn;
    private EditText user_name,user_email,user_dob,user_address;
    private AutoCompleteTextView user_blood_grp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        db=FirebaseDatabase.getInstance();
        reference = db.getReference();
        auth=FirebaseAuth.getInstance();

        reg_btn=findViewById(R.id.reg_btn);
        user_name=findViewById(R.id.user_name);
        user_address=findViewById(R.id.user_address);
        user_email=findViewById(R.id.user_email);
        user_dob=findViewById(R.id.user_dob);
        user_blood_grp=findViewById(R.id.user_blood_grp);


      String item[]={" A+ "," A- "," B+ "," B- "," O+ "," O- "," AB+ "," AB- "};

      ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_item,item);
      user_blood_grp.setAdapter(adapter);


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });































    }

    private void validateData() {

         phone_number=FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
       //  Toast.makeText(this, phone_number, Toast.LENGTH_SHORT).show();
         name=user_name.getText().toString();
         email=user_email.getText().toString();
         dob=user_dob.getText().toString();
         blood_grp=user_blood_grp.getText().toString();
        address = user_address.getText().toString();


        if(name.isEmpty()){
            user_name.setError("Required");
            user_name.requestFocus();
        }
        else if(email.isEmpty()){
            user_email.setError("Required");
            user_email.requestFocus();
        }else if(dob.isEmpty()){

            Toast.makeText(this, "Select DOB", Toast.LENGTH_SHORT).show();

        }else if(blood_grp.isEmpty()){

            Toast.makeText(this, "Select Blood group", Toast.LENGTH_SHORT).show();

        }else if(address.isEmpty()){

            user_address.setError("Required");
            user_address.requestFocus();

        }
        else{
            storeInDataBase();
        }


    }

    private void storeInDataBase() {

        Dialog dialog=new Dialog(RegisterActivity.this);


//String phone_number, String name, String email, String dob, String blood_grp, String address
        UserData data = new UserData(phone_number,name,email,dob,blood_grp,address);

        reference.child("users").child(phone_number).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                dialog.Dismiss();
                if(task.isSuccessful())
                {

                    Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();

                    openMainActivity();
                }else
                {
                    Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                dialog.Dismiss();
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void openMainActivity() {

        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }


//for date of birth//

    public void showDatePickerDialog(View view) {
        // Create a Calendar instance to get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Calculate the maximum date allowed (18 years ago from the current date)
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -18);

        // Create a DatePickerDialog with the current date as the initial selected date,
        // and set the max date as 18 years ago from the current date
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Update the selected date in the TextView or EditText
                String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month + 1, year);
                user_dob.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis()); // Set max date

        // Enable year picker
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

}
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {


    private  String phone_number=null;
    private  String name=null;
    private  String email=null;
    private  String dob=null;
    private  String blood_grp=null;
    private  String userId = null;
    private String state=null;
    private String district=null;
    private String whatsapp=null;
    private String gender=null;
    private int counterValue=0;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private FirebaseDatabase db;
    private Button reg_btn;
    private EditText user_name,user_email,user_dob,user_state,user_district,user_whatsapp;
    private AutoCompleteTextView user_blood_grp,user_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        db=FirebaseDatabase.getInstance();
        reference = db.getReference();
        auth=FirebaseAuth.getInstance();

        reg_btn=findViewById(R.id.reg_btn);
        user_name=findViewById(R.id.user_name);
        user_state=findViewById(R.id.user_state);
        user_district=findViewById(R.id.user_district);
        user_email=findViewById(R.id.user_email);
        user_dob=findViewById(R.id.user_dob);
        user_blood_grp=findViewById(R.id.user_blood_grp);
        user_gender=findViewById(R.id.user_gender);
        user_whatsapp=findViewById(R.id.user_whatsapp);

      String item1[]={" A+ "," A- "," B+ "," B- "," O+ "," O- "," AB+ "," AB- "};
        String item2[]={" Male "," Female "," Other "};

      ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_item,item1);
      user_blood_grp.setAdapter(adapter);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, R.layout.drop_down_item,item2);
        user_gender.setAdapter(ad);


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
        state = user_state.getText().toString();
        district = user_district.getText().toString();
         whatsapp=user_whatsapp.getText().toString();
         gender=user_gender.getText().toString();

        if(name.isEmpty()){
            user_name.setError("Required");
            user_name.requestFocus();
        }
        else if(email.isEmpty()){
            user_email.setError("Required");
            user_email.requestFocus();
        }else if(dob.isEmpty()){

            Toast.makeText(this, "Select DOB", Toast.LENGTH_SHORT).show();

        }else if(gender.isEmpty()){

            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();

        }else if(blood_grp.isEmpty()){

            Toast.makeText(this, "Select Blood group", Toast.LENGTH_SHORT).show();

        }else if(whatsapp.isEmpty()){

            user_whatsapp.setError("Required");
            user_whatsapp.requestFocus();

        }else if(state.isEmpty()){

            user_state.setError("Required");
            user_state.requestFocus();

        }
        else if(district.isEmpty()){

            user_district.setError("Required");
            user_district.requestFocus();

        }
        else{
            storeInDataBase();
        }


    }

    private void storeInDataBase() {



      //  Toast.makeText(this,"userId::"+ userId, Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference("globalCounter")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            counterValue = dataSnapshot.getValue(Integer.class);
                          //  Toast.makeText(RegisterActivity.this, "Counter VAlue::"+counterValue, Toast.LENGTH_SHORT).show();

                            counterValue++;
                            userId=String.format("%08d", counterValue);

                           fun();

                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });



       // Toast.makeText(RegisterActivity.this, "User ID Value 2::"+userId, Toast.LENGTH_SHORT).show();
     
    }

    private void fun(){
        Dialog dialog=new Dialog(RegisterActivity.this);
        whatsapp="+91"+whatsapp;
        UserData data = new UserData(phone_number,name,email,dob,blood_grp,state,district,whatsapp,gender,userId);

        reference.child("users").child(phone_number).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                dialog.Dismiss();
                if(task.isSuccessful())
                {

                    Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    IncreaseGlobalCounter();
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

    void IncreaseGlobalCounter(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("globalCounter");

// Increment the counter value by 1

        databaseReference.setValue(counterValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful())
                {

                }else
                {
                    Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
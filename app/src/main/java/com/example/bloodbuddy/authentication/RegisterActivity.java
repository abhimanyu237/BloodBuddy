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
import com.google.android.material.textfield.TextInputLayout;
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
    private EditText user_name,user_email,user_dob,user_whatsapp;
    private AutoCompleteTextView user_blood_grp,user_gender,user_state,user_district;
    private TextInputLayout textInputLayout;


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
        textInputLayout=findViewById(R.id.user_state_layout);

        String item1[]={" A+ "," A- "," B+ "," B- "," O+ "," O- "," AB+ "," AB- "};
        String item2[]={" Male "," Female "," Other "};

         ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_item,item1);
         user_blood_grp.setAdapter(adapter);

         ArrayAdapter<String> ad = new ArrayAdapter<>(this, R.layout.drop_down_item,item2);
         user_gender.setAdapter(ad);


        String[] indianStates = getResources().getStringArray(R.array.array_indian_states);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,indianStates);



        String[] array_andhra_pradesh_districts = getResources().getStringArray(R.array.array_andhra_pradesh_districts);
        ArrayAdapter<String> array_andhra_pradesh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_andhra_pradesh_districts);

        String[] array_arunachal_pradesh_districts = getResources().getStringArray(R.array.array_arunachal_pradesh_districts);
        ArrayAdapter<String> array_arunachal_pradesh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_arunachal_pradesh_districts);

        String[] array_assam_districts = getResources().getStringArray(R.array.array_assam_districts);
        ArrayAdapter<String> array_assam_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_assam_districts);

        String[] array_bihar_districts = getResources().getStringArray(R.array.array_bihar_districts);
        ArrayAdapter<String> array_bihar_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_bihar_districts);

        String[] array_chhattisgarh_districts = getResources().getStringArray(R.array.array_chhattisgarh_districts);
        ArrayAdapter<String> array_chhattisgarh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_chhattisgarh_districts);

        String[] array_goa_districts = getResources().getStringArray(R.array.array_goa_districts);
        ArrayAdapter<String> array_goa_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_goa_districts);

        String[] array_gujarat_districts = getResources().getStringArray(R.array.array_gujarat_districts);
        ArrayAdapter<String> array_gujarat_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_gujarat_districts);

        String[] array_haryana_districts = getResources().getStringArray(R.array.array_haryana_districts);
        ArrayAdapter<String> array_haryana_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_haryana_districts);

        String[] array_himachal_pradesh_districts = getResources().getStringArray(R.array.array_himachal_pradesh_districts);
        ArrayAdapter<String> array_himachal_pradesh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_himachal_pradesh_districts);

        String[] array_jammu_kashmir_districts = getResources().getStringArray(R.array.array_jammu_kashmir_districts);
        ArrayAdapter<String> array_jammu_kashmir_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_jammu_kashmir_districts);

        String[] array_jharkhand_districts = getResources().getStringArray(R.array.array_jharkhand_districts);
        ArrayAdapter<String> array_jharkhand_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_jharkhand_districts);

        String[] array_karnataka_districts = getResources().getStringArray(R.array.array_karnataka_districts);
        ArrayAdapter<String> array_karnataka_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_karnataka_districts);

        String[] array_kerala_districts = getResources().getStringArray(R.array.array_kerala_districts);
        ArrayAdapter<String> array_kerala_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_kerala_districts);

        String[] array_madhya_pradesh_districts = getResources().getStringArray(R.array.array_madhya_pradesh_districts);
        ArrayAdapter<String> array_madhya_pradesh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_madhya_pradesh_districts);

        String[] array_maharashtra_districts = getResources().getStringArray(R.array.array_maharashtra_districts);
        ArrayAdapter<String> array_maharashtra_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_maharashtra_districts);

        String[] array_manipur_districts = getResources().getStringArray(R.array.array_manipur_districts);
        ArrayAdapter<String> array_manipur_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_manipur_districts);

        String[] array_meghalaya_districts = getResources().getStringArray(R.array.array_meghalaya_districts);
        ArrayAdapter<String> array_meghalaya_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_meghalaya_districts);

        String[] array_mizoram_districts = getResources().getStringArray(R.array.array_mizoram_districts);
        ArrayAdapter<String> array_mizoram_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_mizoram_districts);

        String[] array_nagaland_districts = getResources().getStringArray(R.array.array_nagaland_districts);
        ArrayAdapter<String> array_nagaland_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_nagaland_districts);

        String[] array_odisha_districts = getResources().getStringArray(R.array.array_odisha_districts);
        ArrayAdapter<String> array_odisha_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_odisha_districts);

        String[] array_punjab_districts = getResources().getStringArray(R.array.array_punjab_districts);
        ArrayAdapter<String> array_punjab_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_punjab_districts);

        String[] array_rajasthan_districts = getResources().getStringArray(R.array.array_rajasthan_districts);
        ArrayAdapter<String> array_rajasthan_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_rajasthan_districts);

        String[] array_sikkim_districts = getResources().getStringArray(R.array.array_sikkim_districts);
        ArrayAdapter<String> array_sikkim_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_sikkim_districts);

        String[] array_tamil_nadu_districts = getResources().getStringArray(R.array.array_tamil_nadu_districts);
        ArrayAdapter<String> array_tamil_nadu_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_tamil_nadu_districts);

        String[] array_telangana_districts = getResources().getStringArray(R.array.array_telangana_districts);
        ArrayAdapter<String> array_telangana_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_telangana_districts);

        String[] array_tripura_districts = getResources().getStringArray(R.array.array_tripura_districts);
        ArrayAdapter<String> array_tripura_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_tripura_districts);

        String[] array_uttar_pradesh_districts = getResources().getStringArray(R.array.array_uttar_pradesh_districts);
        ArrayAdapter<String> array_uttar_pradesh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_uttar_pradesh_districts);

        String[] array_uttarakhand_districts = getResources().getStringArray(R.array.array_uttarakhand_districts);
        ArrayAdapter<String> array_uttarakhand_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_uttarakhand_districts);

        String[] array_west_bengal_districts = getResources().getStringArray(R.array.array_west_bengal_districts);
        ArrayAdapter<String> array_west_bengal_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_west_bengal_districts);

        String[] array_andaman_nicobar_districts = getResources().getStringArray(R.array.array_andaman_nicobar_districts);
        ArrayAdapter<String> array_andaman_nicobar_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_andaman_nicobar_districts);

        String[] array_chandigarh_districts = getResources().getStringArray(R.array.array_chandigarh_districts);
        ArrayAdapter<String> array_chandigarh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_chandigarh_districts);

        String[] array_dadra_nagar_haveli_districts = getResources().getStringArray(R.array.array_dadra_nagar_haveli_districts);
        ArrayAdapter<String> array_dadra_nagar_haveli_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_dadra_nagar_haveli_districts);

        String[] array_daman_diu_districts = getResources().getStringArray(R.array.array_daman_diu_districts);
        ArrayAdapter<String> array_daman_diu_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_daman_diu_districts);

        String[] array_delhi_districts = getResources().getStringArray(R.array.array_delhi_districts);
        ArrayAdapter<String> array_delhi_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_delhi_districts);

        String[] array_lakshadweep_districts = getResources().getStringArray(R.array.array_lakshadweep_districts);
        ArrayAdapter<String> array_lakshadweep_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_lakshadweep_districts);

        String[] array_ladakh_districts = getResources().getStringArray(R.array.array_ladakh_districts);
        ArrayAdapter<String> array_ladakh_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_ladakh_districts);

        String[] array_puducherry_districts = getResources().getStringArray(R.array.array_puducherry_districts);
        ArrayAdapter<String> array_puducherry_districtsAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item,array_puducherry_districts);




        user_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                user_state.setAdapter(stateAdapter);
                user_district.setText("");

            }
            });


        user_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                state = user_state.getText().toString();


                switch (state) {
                    case "Andhra Pradesh":
                        user_district.setAdapter(array_andhra_pradesh_districtsAdapter);
                        break;

                    case "Arunachal Pradesh":
                        user_district.setAdapter(array_arunachal_pradesh_districtsAdapter);
                        break;

                    case "Assam":
                        user_district.setAdapter(array_assam_districtsAdapter);
                        break;

                    case "Bihar":
                        user_district.setAdapter(array_bihar_districtsAdapter);
                        break;

                    case "Chhattisgarh":
                        user_district.setAdapter(array_chhattisgarh_districtsAdapter);
                        break;

                    case "Goa":
                        user_district.setAdapter(array_goa_districtsAdapter);
                        break;

                    case "Gujarat":
                        user_district.setAdapter(array_gujarat_districtsAdapter);
                        break;

                    case "Haryana":
                        user_district.setAdapter(array_haryana_districtsAdapter);
                        break;

                    case "Himachal Pradesh":
                        user_district.setAdapter(array_himachal_pradesh_districtsAdapter);
                        break;

                    case "Jammu and Kashmir":
                        user_district.setAdapter(array_jammu_kashmir_districtsAdapter);
                        break;

                    case "Jharkhand":
                        user_district.setAdapter(array_jharkhand_districtsAdapter);
                        break;

                    case "Karnataka":
                        user_district.setAdapter(array_karnataka_districtsAdapter);
                        break;

                    case "Kerala":
                        user_district.setAdapter(array_kerala_districtsAdapter);
                        break;

                    case "Madhya Pradesh":
                        user_district.setAdapter(array_madhya_pradesh_districtsAdapter);
                        break;

                    case "Maharashtra":
                        user_district.setAdapter(array_maharashtra_districtsAdapter);
                        break;

                    case "Manipur":
                        user_district.setAdapter(array_manipur_districtsAdapter);
                        break;

                    case "Meghalaya":
                        user_district.setAdapter(array_meghalaya_districtsAdapter);
                        break;

                    case "Mizoram":
                        user_district.setAdapter(array_mizoram_districtsAdapter);
                        break;

                    case "Nagaland":
                        user_district.setAdapter(array_nagaland_districtsAdapter);
                        break;

                    case "Odisha":
                        user_district.setAdapter(array_odisha_districtsAdapter);
                        break;

                    case "Punjab":
                        user_district.setAdapter(array_punjab_districtsAdapter);
                        break;

                    case "Rajasthan":
                        user_district.setAdapter(array_rajasthan_districtsAdapter);
                        break;

                    case "Sikkim":
                        user_district.setAdapter(array_sikkim_districtsAdapter);
                        break;

                    case "Tamil Nadu":
                        user_district.setAdapter(array_tamil_nadu_districtsAdapter);
                        break;

                    case "Telangana":
                        user_district.setAdapter(array_telangana_districtsAdapter);
                        break;

                    case "Tripura":
                        user_district.setAdapter(array_tripura_districtsAdapter);
                        break;

                    case "Uttar Pradesh":
                        user_district.setAdapter(array_uttar_pradesh_districtsAdapter);
                        break;

                    case "Uttarakhand":
                        user_district.setAdapter(array_uttarakhand_districtsAdapter);
                        break;

                    case "West Bengal":
                        user_district.setAdapter(array_west_bengal_districtsAdapter);
                        break;

                    case "Andaman and Nicobar Islands":
                        user_district.setAdapter(array_andaman_nicobar_districtsAdapter);
                        break;

                    case "Chandigarh":
                        user_district.setAdapter(array_chandigarh_districtsAdapter);
                        break;

                    case "Dadra and Nagar Haveli":
                        user_district.setAdapter(array_dadra_nagar_haveli_districtsAdapter);
                        break;

                    case "Daman and Diu":
                        user_district.setAdapter(array_daman_diu_districtsAdapter);
                        break;

                    case "Delhi":
                        user_district.setAdapter(array_delhi_districtsAdapter);
                        break;

                    case "Lakshadweep":
                        user_district.setAdapter(array_lakshadweep_districtsAdapter);
                        break;

                    case "Ladakh":
                        user_district.setAdapter(array_ladakh_districtsAdapter);
                        break;

                    case "Puducherry":
                        user_district.setAdapter(array_puducherry_districtsAdapter);
                        break;

                    default:
                        Toast.makeText(RegisterActivity.this, "Select state first", Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        });


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

            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();

        }
        else if(district.isEmpty()){

            Toast.makeText(this, "Select District", Toast.LENGTH_SHORT).show();

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
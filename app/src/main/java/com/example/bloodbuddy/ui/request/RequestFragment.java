package com.example.bloodbuddy.ui.request;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbuddy.R;
import com.example.bloodbuddy.authentication.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;


public class RequestFragment extends Fragment {



    private  String phone_number=null;
    private String p_first_name=null;
    private String p_last_name=null;
    private String a_first_name=null;
    private String  a_last_name=null;
    private String State=null;
    private String District=null;
    private String l_address=null;
    private String a_mobile_number=null;
    private String p_age=null;
    private String Units=null;
    private String blood_grp=null;
    private String donate_date=null;
    private String gender=null;
    private String whatsapp=null;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private FirebaseDatabase db;



    private EditText patient_first_name,patient_last_name,attendee_first_name,attendee_last_name,state,district,local_address,
            attendee_mobile_number,patient_age,units,blood_donate_date,contact_whatsapp;
    private AutoCompleteTextView select_blood_grp,patient_gender;
    private Button send_request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      View view=  inflater.inflate(R.layout.fragment_request, container, false);

      patient_first_name=view.findViewById(R.id.patient_first_name);
      patient_last_name=view.findViewById(R.id.patient_last_name);
      attendee_first_name=view.findViewById(R.id.attendee_first_name);
      attendee_last_name=view.findViewById(R.id.attendee_last_name);
      state=view.findViewById(R.id.state);
      district=view.findViewById(R.id.district);
      local_address=view.findViewById(R.id.local_address);
      attendee_mobile_number=view.findViewById(R.id.attendee_mobile_number);
      patient_age=view.findViewById(R.id.patient_age);
      units=view.findViewById(R.id.units);
      patient_gender=view.findViewById(R.id.patient_gender);
      blood_donate_date=view.findViewById(R.id.blood_donate_date);
      contact_whatsapp=view.findViewById(R.id.contact_whatsapp);


      select_blood_grp=view.findViewById(R.id.select_blood_grp);
      send_request=view.findViewById(R.id.send_request);
        db=FirebaseDatabase.getInstance();
        reference = db.getReference();
        auth=FirebaseAuth.getInstance();


        String item1[]={" A+ "," A- "," B+ "," B- "," O+ "," O- "," AB+ "," AB- "};
        String item2[]={" Male "," Female "," Other "};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,item1);
        select_blood_grp.setAdapter(adapter);

        ArrayAdapter<String> ad = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,item2);
        patient_gender.setAdapter(ad);


        blood_donate_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        send_request.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           validateData();

       }
   });














     return view;
    }

    private void validateData() {

        phone_number=FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        p_first_name= patient_first_name.getText().toString();
        p_last_name=  patient_last_name.getText().toString();
        a_first_name= attendee_first_name.getText().toString();
        a_last_name= attendee_last_name.getText().toString();
        State= state.getText().toString();
        District=district.getText().toString();
        l_address=local_address.getText().toString();
        a_mobile_number=  attendee_mobile_number.getText().toString();
        p_age=  patient_age.getText().toString();
        Units=  units.getText().toString();
        blood_grp= select_blood_grp.getText().toString();
         donate_date=blood_donate_date.getText().toString();
         gender=patient_gender.getText().toString();
         whatsapp=contact_whatsapp.getText().toString();


        if( p_first_name.isEmpty()){
            patient_first_name.setError("Required");
            patient_first_name.requestFocus();
        }
        else  if( p_last_name.isEmpty()){
            patient_last_name.setError("Required");
            patient_last_name.requestFocus();
        }
        else  if( a_first_name.isEmpty()){
             attendee_first_name.setError("Required");
             attendee_first_name.requestFocus();
        }
        else  if(a_last_name.isEmpty()){
            attendee_last_name.setError("Required");
            attendee_last_name.requestFocus();
        }
        else if(a_mobile_number.isEmpty()){
            attendee_mobile_number.setError("Required");
            attendee_mobile_number.requestFocus();
        }else if(p_age.isEmpty()){

            patient_age.setError("Required");
            patient_age.requestFocus();

        }else if(gender.isEmpty()){

            patient_gender.setError("Required");
            patient_gender.requestFocus();

        }else if(blood_grp.isEmpty()){

            select_blood_grp.setError("Required");
            select_blood_grp.requestFocus();

        }else if(donate_date.isEmpty()){

            blood_donate_date.requestFocus();
            blood_donate_date.setError("Required");

        }else  if(whatsapp.isEmpty()){
            contact_whatsapp.setError("Required");
            contact_whatsapp.requestFocus();
        }
        else  if(State.isEmpty()){
            state.setError("Required");
            state.requestFocus();
        }
        else  if(District.isEmpty()){
            district.setError("Required");
            district.requestFocus();
        }
        else  if( l_address.isEmpty()){
            local_address.setError("Required");
            local_address.requestFocus();
        }
        else if(Units.isEmpty()){
            units.setError("Required");
            units.requestFocus();


        }
        else{
            storeInDataBase();
        }


    }

    private void storeInDataBase() {





        RequestData data = new RequestData(p_first_name, p_last_name, a_first_name, a_last_name,
                State, District, l_address, a_mobile_number, p_age,
                Units, blood_grp,donate_date,gender,whatsapp);

        reference.child("requests").child(State).child(District).child(phone_number).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful())
                {

                    Toast.makeText(getContext(), "Request Send Successfully", Toast.LENGTH_SHORT).show();


                }else
                {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void showDatePickerDialog(View view) {
        // Create a Calendar instance to get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog with the current date as the initial selected date
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Update the selected date in the TextView or EditText
                String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month + 1, year);
                blood_donate_date.setText(selectedDate);
            }
        }, year, month, day);

        // Set the minimum date to today
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        // Set date picker properties
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }




}
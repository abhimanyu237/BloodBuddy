package com.example.bloodbuddy.ui.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbuddy.R;
import com.example.bloodbuddy.ui.request.RequestData;

import org.w3c.dom.Text;

public class AcceptActivity extends AppCompatActivity {

    private TextView patient_name,patient_age,patient_bg,patient_gender,bg_date,units,attendee_name,attendee_mob,
            attendee_whatsapp,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

        RequestData requestData = (RequestData) getIntent().getSerializableExtra("requestData");



        patient_name=findViewById(R.id.patient_name);
                patient_age=findViewById(R.id.patient_age);
                patient_bg=findViewById(R.id.  patient_bg);
                patient_gender=findViewById(R.id.patient_gender);
                bg_date=findViewById(R.id. bg_date);
                units=findViewById(R.id.  units);
                attendee_name=findViewById(R.id.attendee_name);
                attendee_mob=findViewById(R.id.  attendee_mob);
                attendee_whatsapp=findViewById(R.id.attendee_whatsapp);
                address=findViewById(R.id.address);




        if (requestData != null) {

            patient_name.setText(requestData.getPatient_first_name()+" "+requestData.getPatient_last_name());
                    patient_age.setText(requestData.getPatient_age());
            patient_bg.setText(requestData.getSelect_blood_grp());
                    patient_gender.setText(requestData.getGender());
            bg_date.setText(requestData.getDonate_date());
                    units.setText(requestData.getUnits());
            attendee_name.setText(requestData.getAttendee_first_name()+" "+requestData.getAttendee_last_name());
                    attendee_mob.setText(requestData.getAttendee_mobile_number());
            attendee_whatsapp.setText(requestData.getWhatsapp());
                    address.setText(requestData.getState()+" , "+requestData.getDistrict()+" , "+requestData.getLocal_address());
        }
        else
            Toast.makeText(this, "null hu", Toast.LENGTH_SHORT).show();




    }



}
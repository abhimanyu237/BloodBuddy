package com.example.bloodbuddy.ui.request;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import java.util.Objects;
import java.util.UUID;

import android.content.Intent;
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

import com.example.bloodbuddy.MainActivity;
import com.example.bloodbuddy.Navigation_Profile;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.UserData;
import com.example.bloodbuddy.authentication.RegisterActivity;
import com.example.bloodbuddy.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;


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



    private EditText patient_first_name,patient_last_name,attendee_first_name,attendee_last_name,local_address,
            attendee_mobile_number,patient_age,units,blood_donate_date,contact_whatsapp;
    private AutoCompleteTextView select_blood_grp,patient_gender,state,district;
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
        attendee_mobile_number.setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().toString());
        attendee_mobile_number.setEnabled(false);
         datax();
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



        String[] indianStates = getResources().getStringArray(R.array.array_indian_states);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,indianStates);
        state.setAdapter(stateAdapter);




        String[] array_andhra_pradesh_districts = getResources().getStringArray(R.array.array_andhra_pradesh_districts);
        ArrayAdapter<String> array_andhra_pradesh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_andhra_pradesh_districts);

        String[] array_arunachal_pradesh_districts = getResources().getStringArray(R.array.array_arunachal_pradesh_districts);
        ArrayAdapter<String> array_arunachal_pradesh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_arunachal_pradesh_districts);

        String[] array_assam_districts = getResources().getStringArray(R.array.array_assam_districts);
        ArrayAdapter<String> array_assam_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_assam_districts);

        String[] array_bihar_districts = getResources().getStringArray(R.array.array_bihar_districts);
        ArrayAdapter<String> array_bihar_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_bihar_districts);

        String[] array_chhattisgarh_districts = getResources().getStringArray(R.array.array_chhattisgarh_districts);
        ArrayAdapter<String> array_chhattisgarh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_chhattisgarh_districts);

        String[] array_goa_districts = getResources().getStringArray(R.array.array_goa_districts);
        ArrayAdapter<String> array_goa_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_goa_districts);

        String[] array_gujarat_districts = getResources().getStringArray(R.array.array_gujarat_districts);
        ArrayAdapter<String> array_gujarat_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_gujarat_districts);

        String[] array_haryana_districts = getResources().getStringArray(R.array.array_haryana_districts);
        ArrayAdapter<String> array_haryana_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_haryana_districts);

        String[] array_himachal_pradesh_districts = getResources().getStringArray(R.array.array_himachal_pradesh_districts);
        ArrayAdapter<String> array_himachal_pradesh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_himachal_pradesh_districts);

        String[] array_jammu_kashmir_districts = getResources().getStringArray(R.array.array_jammu_kashmir_districts);
        ArrayAdapter<String> array_jammu_kashmir_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_jammu_kashmir_districts);

        String[] array_jharkhand_districts = getResources().getStringArray(R.array.array_jharkhand_districts);
        ArrayAdapter<String> array_jharkhand_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_jharkhand_districts);

        String[] array_karnataka_districts = getResources().getStringArray(R.array.array_karnataka_districts);
        ArrayAdapter<String> array_karnataka_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_karnataka_districts);

        String[] array_kerala_districts = getResources().getStringArray(R.array.array_kerala_districts);
        ArrayAdapter<String> array_kerala_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_kerala_districts);

        String[] array_madhya_pradesh_districts = getResources().getStringArray(R.array.array_madhya_pradesh_districts);
        ArrayAdapter<String> array_madhya_pradesh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_madhya_pradesh_districts);

        String[] array_maharashtra_districts = getResources().getStringArray(R.array.array_maharashtra_districts);
        ArrayAdapter<String> array_maharashtra_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_maharashtra_districts);

        String[] array_manipur_districts = getResources().getStringArray(R.array.array_manipur_districts);
        ArrayAdapter<String> array_manipur_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_manipur_districts);

        String[] array_meghalaya_districts = getResources().getStringArray(R.array.array_meghalaya_districts);
        ArrayAdapter<String> array_meghalaya_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_meghalaya_districts);

        String[] array_mizoram_districts = getResources().getStringArray(R.array.array_mizoram_districts);
        ArrayAdapter<String> array_mizoram_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_mizoram_districts);

        String[] array_nagaland_districts = getResources().getStringArray(R.array.array_nagaland_districts);
        ArrayAdapter<String> array_nagaland_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_nagaland_districts);

        String[] array_odisha_districts = getResources().getStringArray(R.array.array_odisha_districts);
        ArrayAdapter<String> array_odisha_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_odisha_districts);

        String[] array_punjab_districts = getResources().getStringArray(R.array.array_punjab_districts);
        ArrayAdapter<String> array_punjab_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_punjab_districts);

        String[] array_rajasthan_districts = getResources().getStringArray(R.array.array_rajasthan_districts);
        ArrayAdapter<String> array_rajasthan_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_rajasthan_districts);

        String[] array_sikkim_districts = getResources().getStringArray(R.array.array_sikkim_districts);
        ArrayAdapter<String> array_sikkim_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_sikkim_districts);

        String[] array_tamil_nadu_districts = getResources().getStringArray(R.array.array_tamil_nadu_districts);
        ArrayAdapter<String> array_tamil_nadu_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_tamil_nadu_districts);

        String[] array_telangana_districts = getResources().getStringArray(R.array.array_telangana_districts);
        ArrayAdapter<String> array_telangana_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_telangana_districts);

        String[] array_tripura_districts = getResources().getStringArray(R.array.array_tripura_districts);
        ArrayAdapter<String> array_tripura_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_tripura_districts);

        String[] array_uttar_pradesh_districts = getResources().getStringArray(R.array.array_uttar_pradesh_districts);
        ArrayAdapter<String> array_uttar_pradesh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_uttar_pradesh_districts);

        String[] array_uttarakhand_districts = getResources().getStringArray(R.array.array_uttarakhand_districts);
        ArrayAdapter<String> array_uttarakhand_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_uttarakhand_districts);

        String[] array_west_bengal_districts = getResources().getStringArray(R.array.array_west_bengal_districts);
        ArrayAdapter<String> array_west_bengal_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_west_bengal_districts);

        String[] array_andaman_nicobar_districts = getResources().getStringArray(R.array.array_andaman_nicobar_districts);
        ArrayAdapter<String> array_andaman_nicobar_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_andaman_nicobar_districts);

        String[] array_chandigarh_districts = getResources().getStringArray(R.array.array_chandigarh_districts);
        ArrayAdapter<String> array_chandigarh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_chandigarh_districts);

        String[] array_dadra_nagar_haveli_districts = getResources().getStringArray(R.array.array_dadra_nagar_haveli_districts);
        ArrayAdapter<String> array_dadra_nagar_haveli_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_dadra_nagar_haveli_districts);

        String[] array_daman_diu_districts = getResources().getStringArray(R.array.array_daman_diu_districts);
        ArrayAdapter<String> array_daman_diu_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_daman_diu_districts);

        String[] array_delhi_districts = getResources().getStringArray(R.array.array_delhi_districts);
        ArrayAdapter<String> array_delhi_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_delhi_districts);

        String[] array_lakshadweep_districts = getResources().getStringArray(R.array.array_lakshadweep_districts);
        ArrayAdapter<String> array_lakshadweep_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_lakshadweep_districts);

        String[] array_ladakh_districts = getResources().getStringArray(R.array.array_ladakh_districts);
        ArrayAdapter<String> array_ladakh_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_ladakh_districts);

        String[] array_puducherry_districts = getResources().getStringArray(R.array.array_puducherry_districts);
        ArrayAdapter<String> array_puducherry_districtsAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,array_puducherry_districts);




        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                district.setText("");

            }
        });


        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                State = state.getText().toString();


                switch (State) {
                    case "Andhra Pradesh":
                        district.setAdapter(array_andhra_pradesh_districtsAdapter);
                        break;

                    case "Arunachal Pradesh":
                        district.setAdapter(array_arunachal_pradesh_districtsAdapter);
                        break;

                    case "Assam":
                        district.setAdapter(array_assam_districtsAdapter);
                        break;

                    case "Bihar":
                        district.setAdapter(array_bihar_districtsAdapter);
                        break;

                    case "Chhattisgarh":
                        district.setAdapter(array_chhattisgarh_districtsAdapter);
                        break;

                    case "Goa":
                        district.setAdapter(array_goa_districtsAdapter);
                        break;

                    case "Gujarat":
                        district.setAdapter(array_gujarat_districtsAdapter);
                        break;

                    case "Haryana":
                        district.setAdapter(array_haryana_districtsAdapter);
                        break;

                    case "Himachal Pradesh":
                        district.setAdapter(array_himachal_pradesh_districtsAdapter);
                        break;

                    case "Jammu and Kashmir":
                        district.setAdapter(array_jammu_kashmir_districtsAdapter);
                        break;

                    case "Jharkhand":
                        district.setAdapter(array_jharkhand_districtsAdapter);
                        break;

                    case "Karnataka":
                        district.setAdapter(array_karnataka_districtsAdapter);
                        break;

                    case "Kerala":
                        district.setAdapter(array_kerala_districtsAdapter);
                        break;

                    case "Madhya Pradesh":
                        district.setAdapter(array_madhya_pradesh_districtsAdapter);
                        break;

                    case "Maharashtra":
                        district.setAdapter(array_maharashtra_districtsAdapter);
                        break;

                    case "Manipur":
                        district.setAdapter(array_manipur_districtsAdapter);
                        break;

                    case "Meghalaya":
                        district.setAdapter(array_meghalaya_districtsAdapter);
                        break;

                    case "Mizoram":
                        district.setAdapter(array_mizoram_districtsAdapter);
                        break;

                    case "Nagaland":
                        district.setAdapter(array_nagaland_districtsAdapter);
                        break;

                    case "Odisha":
                        district.setAdapter(array_odisha_districtsAdapter);
                        break;

                    case "Punjab":
                        district.setAdapter(array_punjab_districtsAdapter);
                        break;

                    case "Rajasthan":
                        district.setAdapter(array_rajasthan_districtsAdapter);
                        break;

                    case "Sikkim":
                        district.setAdapter(array_sikkim_districtsAdapter);
                        break;

                    case "Tamil Nadu":
                        district.setAdapter(array_tamil_nadu_districtsAdapter);
                        break;

                    case "Telangana":
                        district.setAdapter(array_telangana_districtsAdapter);
                        break;

                    case "Tripura":
                        district.setAdapter(array_tripura_districtsAdapter);
                        break;

                    case "Uttar Pradesh":
                        district.setAdapter(array_uttar_pradesh_districtsAdapter);
                        break;

                    case "Uttarakhand":
                        district.setAdapter(array_uttarakhand_districtsAdapter);
                        break;

                    case "West Bengal":
                        district.setAdapter(array_west_bengal_districtsAdapter);
                        break;

                    case "Andaman and Nicobar Islands":
                        district.setAdapter(array_andaman_nicobar_districtsAdapter);
                        break;

                    case "Chandigarh":
                        district.setAdapter(array_chandigarh_districtsAdapter);
                        break;

                    case "Dadra and Nagar Haveli":
                        district.setAdapter(array_dadra_nagar_haveli_districtsAdapter);
                        break;

                    case "Daman and Diu":
                        district.setAdapter(array_daman_diu_districtsAdapter);
                        break;

                    case "Delhi":
                        district.setAdapter(array_delhi_districtsAdapter);
                        break;

                    case "Lakshadweep":
                        district.setAdapter(array_lakshadweep_districtsAdapter);
                        break;

                    case "Ladakh":
                        district.setAdapter(array_ladakh_districtsAdapter);
                        break;

                    case "Puducherry":
                        district.setAdapter(array_puducherry_districtsAdapter);
                        break;

                    default:
                        Toast.makeText(getContext(), "Select state first", Toast.LENGTH_SHORT).show();
                        break;
                }


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
            Toast.makeText(getContext(), "Select State", Toast.LENGTH_SHORT).show();
        }
        else  if(District.isEmpty()){
            Toast.makeText(getContext(), "Select District", Toast.LENGTH_SHORT).show();

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


        String uniqueID = UUID.randomUUID().toString();
        String code=phone_number+uniqueID;


        RequestData data = new RequestData(p_first_name, p_last_name, a_first_name, a_last_name,
                State, District, l_address, a_mobile_number, p_age,
                Units, blood_grp,donate_date,gender,whatsapp,code);



        reference.child("requests").child(State).child(District).child(code).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful())
                {

                    Toast.makeText(getContext(), "Request Send Successfully", Toast.LENGTH_SHORT).show();
                    StoreDataForUserRequest(code ,data);

                    startActivity(new Intent(getActivity(), MainActivity.class));
                    requireActivity().finish();

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

    private void datax(){
        FirebaseDatabase.getInstance().getReference("users")
                .child(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhoneNumber()))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                        {
                            UserData userData = dataSnapshot.getValue(UserData.class);

                            assert userData != null;

                            String[] Attendee_name=userData.getName().toString().split("\\s+");
                            attendee_first_name.setText(Attendee_name[0]);
                            //attendee_first_name.setEnabled(false);
                            if(Attendee_name.length>1) {
                                attendee_last_name.setText(Attendee_name[1]);
                                //attendee_last_name.setEnabled(false);
                            }else{
                                attendee_last_name.setText("NA");
                            }

//                                attendee_mobile_number.setText(userData.getPhoneNumber().toString());
//                                attendee_mobile_number.setEnabled(false);
                        }



                    }
                });
    }

private void StoreDataForUserRequest(String code,RequestData data){

     FirebaseDatabase.getInstance().getReference().child("userRequests").child(phone_number).child("openRequests").child(code).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {


             if(!task.isSuccessful())
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

}


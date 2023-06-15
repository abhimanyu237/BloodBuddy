package com.example.bloodbuddy;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbuddy.authentication.LoginActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Navigation_Profile extends AppCompatActivity {
    private TextView UserId,Name,Phone,District,Email,WhatsApp,Blood_Type,DOB,Gender;
    private ImageView profile;
    private FirebaseAuth auth=FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_profile);
        UserId=findViewById(R.id.userid);
        Name=findViewById(R.id.name);
        Phone=findViewById(R.id.phone);
        District=findViewById(R.id.district);
        Email=findViewById(R.id.email);
        WhatsApp=findViewById(R.id.whatsapp);
        Blood_Type=findViewById(R.id.blood);
        DOB=findViewById(R.id.dob);
        Gender=findViewById(R.id.gender);
        profile=findViewById(R.id.picture);
        Button logout = findViewById(R.id.logout);
        ProfileData();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickLogout();
            }
        });
    }
    private void  ProfileData(){


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

                            UserId.setText("UID : "+userData.getUserId());

                            Name.setText(userData.getName());

                            Blood_Type.setText("Blood Type :"+userData.getBlood_grp());

                            Phone.setText("Phone : "+userData.getPhone_number());

                            District.setText("District : "+userData.getDistrict());

                            Email.setText("Email : "+ userData.getEmail());

                            WhatsApp.setText("WhatsApp : "+userData.getWhatsapp());

                            DOB.setText("Date of Birth : "+userData.getDob());

                            String temp=userData.getGender();
                            Gender.setText("Gender : "+temp);
                              System.out.println(temp.toUpperCase());
                             if(temp.equalsIgnoreCase(" MALE ")){
                                 profile.setImageResource(R.drawable.malep);
                             }else if(temp.equalsIgnoreCase(" FEMALE ")){
                                 profile.setImageResource(R.drawable.female);
                             }
                        }
                        else
                        {
                            Toast.makeText(Navigation_Profile.this, "profile loading", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }
    public void OnclickLogout(){
        // Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(Navigation_Profile.this);
        builder.setMessage("Do you want to Logout");
        builder.setTitle("Bloodbuddy");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            auth.signOut();
            finish();
            startActivity(new Intent(Navigation_Profile.this, LoginActivity.class));
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
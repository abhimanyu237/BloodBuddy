package com.example.bloodbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;

import com.example.bloodbuddy.authentication.LoginActivity;
import com.example.bloodbuddy.authentication.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {
       private int result=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_splash_screen);
        checkUser();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (result == 1) {
                    // User data is saved in the database, navigate to main activity
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
                else if(result==2){
                    // User data is not saved in the database, navigate to register activity
                    startActivity(new Intent(SplashScreenActivity.this, RegisterActivity.class));
                    finish();
                }
                else if(result==3){
                    // User is not authenticated, navigate to login activity
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 5000);



    }

    private void checkUser() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is already authenticated, check if user data is saved in the database
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getPhoneNumber());
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User data is saved in the database, navigate to main activity
//                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                        result=1;
                    } else {
                        // User data is not saved in the database, navigate to register activity
//                        startActivity(new Intent(SplashScreenActivity.this, RegisterActivity.class));
                        result=2;
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error if needed
                }
            });
        } else {
            // User is not authenticated, navigate to login activity
            result=3;
//            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));

        }
    }

}
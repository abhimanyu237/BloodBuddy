package com.example.bloodbuddy.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbuddy.MainActivity;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.SplashScreenActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private MaterialCardView numberLayout;
    private FirebaseAuth auth;

    private MaterialCardView otpLayout;
    private Button send_otp_btn,verify_otp_btn;
    private EditText user_number,user_otp;
    private String VerificationId=null;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        numberLayout=findViewById(R.id.numberLayout);
        otpLayout=findViewById(R.id.otpLayout);
        send_otp_btn=findViewById(R.id.send_Otp_btn);
        verify_otp_btn=findViewById(R.id.verifyOtp_btn);
        user_number=findViewById(R.id.user_number);
        user_otp=findViewById(R.id.user_otp);
        auth=FirebaseAuth.getInstance();


        send_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String number=user_number.getText().toString();

                if(number==null)
                    Toast.makeText(LoginActivity.this, "Please enter your number", Toast.LENGTH_SHORT).show();
                else
                {
                     sendOtp(number);
                }


            }
        });

        verify_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=user_otp.getText().toString();
              //  Toast.makeText(LoginActivity.this, otp, Toast.LENGTH_SHORT).show();
                if(otp==null)
                    Toast.makeText(LoginActivity.this, "Enter your otp", Toast.LENGTH_SHORT).show();
                else
                verifyOtp(otp);
            }
        });
    }

    private void verifyOtp(String otp) {
       // Toast.makeText(this, VerificationId+"  +  "+otp, Toast.LENGTH_SHORT).show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void sendOtp(String number) {


        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                } 
                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                // Save verification ID and resending token so we can use them later


                VerificationId = verificationId;

                numberLayout.setVisibility(View.GONE);
                otpLayout.setVisibility(View.VISIBLE);
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91"+number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

     //   Toast.makeText(this, "160", Toast.LENGTH_SHORT).show();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            checkUser("+91"+user_number.getText().toString());

                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    private void checkUser(String number) {


            // User is already authenticated, check if user data is saved in the database
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(number);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User data is saved in the database, navigate to main activity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        // User data is not saved in the database, navigate to register activity
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    }
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error if needed
                }
            });

    }
}
package com.example.bloodbuddy.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bloodbuddy.HealthTipsActivity;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.UserData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {


    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.app_name);
    }

    private TextView userId,bloodGrp;
    private String uid=null,bg=null;
    private CardView card3,card4,card5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view=inflater.inflate(R.layout.fragment_home, container, false);


    userId=view.findViewById(R.id.userId);
    bloodGrp=view.findViewById(R.id.bloodGrp);
    card3=view.findViewById(R.id.card3);
    card4=view.findViewById(R.id.card4);
    card5=view.findViewById(R.id.card5);

    getUserIdAndBloodGrp();

//    userId.setText(uid);

       card3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), HealthTipsActivity.class));
    }
});

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); intent.putExtra(Intent.EXTRA_SUBJECT,  "Check out this cool Application");
                intent.putExtra(Intent.EXTRA_TEXT, "\uD83E\uDE78 Be a Life Saver! \uD83E\uDE78\uD83D\uDCAA Join me in making a difference by using this incredible blood donation app! \uD83D\uDCF1\uD83D\uDC95 Together, we can save lives and give hope to those in need. ❤️ Download now and be a hero in someone's life! \uD83E\uDDB8\u200D♂️\uD83E\uDDB8\u200D♀️ #DonateBlood #BeAHero #SaveLives: https://drive.google.com/drive/folders/1gc4hDJKEAMs2SNgrB4NfvaHsyFZZX0__?usp=sharing");
                startActivity(Intent.createChooser(intent ,"Share Via"));
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://twitter.com/BloodBuddy2023");
            }
        });


     return view;
    }
    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SlideModel> imageList1 = new ArrayList<>(); // Create image list
        imageList1.add(new SlideModel("https://cdn.pixabay.com/photo/2017/01/10/07/11/blood-1968458_1280.png", ScaleTypes.FIT));
        imageList1.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7TTA9Jkr4ZV4zLpXrIOtAZ7cNkFL18dTX0aknJF3wAiym9ZLrvmY_l7nIIZFVpCsFwA&usqp=CAU",ScaleTypes.FIT));
        imageList1.add(new SlideModel("https://media.istockphoto.com/id/1164063949/photo/tubes-of-blood-sample-for-testing-medical-equipment.jpg?s=612x612&w=0&k=20&c=I0X30LIMzUp3qlLDZQSwAhgnng75R3Bl7WBy1WPSfdM=",ScaleTypes.FIT));
        ImageSlider imageSlider1=view.findViewById(R.id.image_slider1);
        imageSlider1.setImageList(imageList1);
    }


    private void     getUserIdAndBloodGrp(){


        FirebaseDatabase.getInstance().getReference("users")
                .child(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhoneNumber()))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                        {
                            UserData userData = dataSnapshot.getValue(UserData.class);

                            assert userData != null;
                            uid="UID :"+userData.getUserId().trim();
                            bg="Blood Grp :"+userData.getBlood_grp();
                            userId.setText(uid);
                            bloodGrp.setText(bg);
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }
}
package com.example.bloodbuddy.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view=inflater.inflate(R.layout.fragment_home, container, false);


    userId=view.findViewById(R.id.userId);
    bloodGrp=view.findViewById(R.id.bloodGrp);


    getUserIdAndBloodGrp();

//    userId.setText(uid);





     return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SlideModel> imageList1 = new ArrayList<>(); // Create image list
        imageList1.add(new SlideModel("https://f8540e.p3cdn2.secureserver.net/wp-content/uploads/BG-NEW-WEB.jpg?time=1686752190", ScaleTypes.FIT));
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
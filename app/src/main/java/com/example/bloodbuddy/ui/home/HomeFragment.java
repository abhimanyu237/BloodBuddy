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

public class HomeFragment extends Fragment {


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
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
//        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list
//        imageList.add(new SlideModel("https://eaglestaleonline.com/wp-content/uploads/2020/10/News-Vein-Drain-Infographic-2.png","Motivation",ScaleTypes.CENTER_CROP));
//        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoRTL57Z-ux_hpkpGfaMlJPj4iwvOEDqQ-dA&usqp=CAU", "Blood Donor Motivation",ScaleTypes.CENTER_CROP));
//        imageList.add(new SlideModel("https://i.ytimg.com/vi/87aP7m9NcyM/maxresdefault.jpg", "“HIGHEST BLOOD DONOR\" is achieved by Hony Capt Dr. Suresh Kumar Saini from Karnal (Haryana) India on 22nd October 2020. He donated blood 131 times. He also donated platelets for 94 times.",ScaleTypes.CENTER_CROP));
//        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
//        imageSlider.setImageList(imageList);

        ArrayList<SlideModel> imageList1 = new ArrayList<>(); // Create image list

        imageList1.add(new SlideModel("https://c8.alamy.com/comp/2M4KYNM/blood-donationorgan-transplantation-laboratory-mobile-app-page-onboard-screenvolunteer-character-healthcarecharity-world-donor-day-concept-for-we-2M4KYNM.jpg", "blood Buddy", ScaleTypes.CENTER_CROP));
        imageList1.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt6qVMVE-6gnf_TofUC1R_xUuqSlDw7kJUmCwdNVN-DuJ6K7B7aumtYZVrWIvjd5JpQls&usqp=CAU", "Blood Groups",ScaleTypes.CENTER_CROP));
        imageList1.add(new SlideModel("https://bit.ly/3fLJf72", "And people do that.",ScaleTypes.CENTER_CROP));
        ImageSlider imageSlider1=view.findViewById(R.id.image_slider1);
        imageSlider1.setImageList(imageList1);
    }


    private void     getUserIdAndBloodGrp(){


        FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                        {
                            UserData userData = dataSnapshot.getValue(UserData.class);

                            uid="UID :"+userData.getUserId();
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
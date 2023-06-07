package com.example.bloodbuddy.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bloodbuddy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list
        imageList.add(new SlideModel("https://eaglestaleonline.com/wp-content/uploads/2020/10/News-Vein-Drain-Infographic-2.png","Motivation",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoRTL57Z-ux_hpkpGfaMlJPj4iwvOEDqQ-dA&usqp=CAU", "Blood Donor Motivation",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.ytimg.com/vi/87aP7m9NcyM/maxresdefault.jpg", "“HIGHEST BLOOD DONOR\" is achieved by Hony Capt Dr. Suresh Kumar Saini from Karnal (Haryana) India on 22nd October 2020. He donated blood 131 times. He also donated platelets for 94 times.",ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
        ArrayList<SlideModel> imageList1 = new ArrayList<>(); // Create image list

        imageList1.add(new SlideModel("https://c8.alamy.com/comp/2M4KYNM/blood-donationorgan-transplantation-laboratory-mobile-app-page-onboard-screenvolunteer-character-healthcarecharity-world-donor-day-concept-for-we-2M4KYNM.jpg", "blood Buddy", ScaleTypes.CENTER_CROP));
        imageList1.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt6qVMVE-6gnf_TofUC1R_xUuqSlDw7kJUmCwdNVN-DuJ6K7B7aumtYZVrWIvjd5JpQls&usqp=CAU", "Blood Groups",ScaleTypes.CENTER_CROP));
        imageList1.add(new SlideModel("https://bit.ly/3fLJf72", "And people do that.",ScaleTypes.CENTER_CROP));
        ImageSlider imageSlider1=view.findViewById(R.id.image_slider1);
        imageSlider1.setImageList(imageList1);
    }
}
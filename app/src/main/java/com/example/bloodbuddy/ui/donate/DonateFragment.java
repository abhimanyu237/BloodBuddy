package com.example.bloodbuddy.ui.donate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.adapters.ViewPagerAdapter;
import com.example.bloodbuddy.R;
import com.example.bloodbuddy.ViewPagerRequestsAdapter;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class DonateFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_donate, container, false);

        tabLayout=view.findViewById(R.id.tabLayout);
        viewPager=view.findViewById(R.id.viewPager);


        ViewPagerDonateAdapter adapter = new ViewPagerDonateAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);



        tabLayout.setupWithViewPager(viewPager);
      return view;
    }
}
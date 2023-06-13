package com.example.bloodbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Navigation_Request_History extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_request_history);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);


    ViewPagerRequestsAdapter adapter = new ViewPagerRequestsAdapter(getSupportFragmentManager());
    viewPager.setAdapter(adapter);



   tabLayout.setupWithViewPager(viewPager);


    }
}
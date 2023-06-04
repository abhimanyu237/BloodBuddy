package com.example.bloodbuddy.ui.donate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerDonateAdapter extends FragmentPagerAdapter {
    public ViewPagerDonateAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new YourBloodGroupFragment();
        }
        else
            return new OtherBloodGroupFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position==0)
            return "Your Blood Group";
        else
            return "Other Blood Group";
    }
}

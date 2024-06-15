package com.example.educonnectadmin.Assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public FragmentAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return list.get(position);


    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "6th";
                break;
            case 1:
                title = "7th";
                break;
            case 2:
                title = "8th";
                break;
            case 3:
                title = "9th";
                break;
            case 4:
                title = "10th";
                break;
            case 5:
                title = "11th";
                break;
            case 6:
                title = "12th";
                break;


        }


        return title;
    }
}

package com.example.educonnectadmin.Assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.educonnectadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AssignmentList extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_list);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UploadAssignment.class));
            }
        });
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();


        fragmentArrayList.add(new Sixth());
        fragmentArrayList.add(new Seventh());
        fragmentArrayList.add(new Eighth());
        fragmentArrayList.add(new Ninth());
        fragmentArrayList.add(new Tenth());
        fragmentArrayList.add(new Eleventh());
        fragmentArrayList.add(new Twelvth());

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentArrayList));
        viewPager.setOffscreenPageLimit(4);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
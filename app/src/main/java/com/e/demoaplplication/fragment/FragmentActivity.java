package com.e.demoaplplication.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.e.demoaplplication.R;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

public class FragmentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ViewPager viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tablayout);
         
       tabLayout.setupWithViewPager(viewPager);


    }




}

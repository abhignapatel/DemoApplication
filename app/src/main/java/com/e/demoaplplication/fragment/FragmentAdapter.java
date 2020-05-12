package com.e.demoaplplication.fragment;

import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.e.demoaplplication.bean.PostList;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    FragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i==0){
           return new SearchFragment();
        }else{
            return new FavoriteFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


}

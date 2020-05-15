package com.e.demoaplplication.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


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

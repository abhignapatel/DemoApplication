package com.e.demoaplplication.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.e.demoaplplication.R;
import com.e.demoaplplication.bean.FavoriteModel;
import com.e.demoaplplication.bean.PostModel;
import com.e.demoaplplication.databinding.ActivityFragmentBinding;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import com.e.demoaplplication.database.FavDataBase;

public class FragmentActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    private SearchFragment searchFragment = new SearchFragment();
    private FavoriteFragment favoriteFragment = new FavoriteFragment();
    private FavDataBase favDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      ActivityFragmentBinding activityFragmentBinding= DataBindingUtil.setContentView(this,R.layout.activity_fragment);
        favDataBase = new FavDataBase(getApplicationContext());

        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tablayout);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("search");
        tabLayout.getTabAt(1).setText("Favorite");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ("Favorite".equals(tab.getText())) {
                    //show favorite data on favorite fragment
                    List<PostModel> allData = favDataBase.getAllData();
                    //click the new element nd show in the favorite fragment
                    List<FavoriteModel> favoriteModels = new ArrayList<>();

                    for (int i = 0; i < allData.size(); i++) {
                        PostModel postModel = allData.get(i);
                        FavoriteModel favoriteModel = new FavoriteModel();
                        //arrive new element in favorite list and show on the favorite fragment
                        favoriteModel.setAvatarUrl(postModel.getAvatarUrl());
                        favoriteModel.setName(postModel.getName());
                        favoriteModel.setLogin(postModel.getLogin());

                        favoriteModels.add(favoriteModel); //add to new element into favoriteList fragment
                    }

                    favoriteFragment.setFavoriteModel(favoriteModels);

                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int i) {
            if (i == 0) {
                return searchFragment;
            } else {
                return favoriteFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }


    }
}

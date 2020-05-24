package com.e.demoaplplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.e.demoaplplication.bean.PostModel;
import com.e.demoaplplication.databinding.ActivityItemBinding;


public class ItemActivity extends AppCompatActivity {

    public static final String TAG = "itemactivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityItemBinding activityItemBinding = DataBindingUtil.setContentView(this,R.layout.activity_item);

        Intent intent = getIntent();
        PostModel postModel = (PostModel) intent.getSerializableExtra("click");
        activityItemBinding.setPostModel(postModel);
        if (postModel != null) {
            if (postModel.getName() != null) {
                Log.d(TAG, postModel.getName());
            }else{
                Log.d(TAG,"name is null");
            }
            if (postModel.getLogin() != null) {
                Log.d(TAG, postModel.getLogin());
            }else{
                Log.d(TAG,"login is null");
            }
            if (postModel.getEmail() != null) {
                Log.d(TAG, postModel.getEmail());
            }else{
                Log.d(TAG,"email is null");
            }
            if (postModel.getCompany() != null) {
                Log.d(TAG, postModel.getCompany());
            }else{
                Log.d(TAG,"company is null");
            }
            if (postModel.getFollowerUrl()!= null) {
                Log.d(TAG, postModel.getFollowerUrl());
            }else{
                Log.d(TAG,"followerurl is null");
            }
            if (postModel.getAvatarUrl()!= null) {
                Log.d(TAG, String.valueOf(Glide.with(getApplicationContext()).load(postModel.getAvatarUrl()).into(activityItemBinding.avatarUrl)));
            }else{
                Log.d(TAG,"avatarUrl is null");
            }

        }

    }

}

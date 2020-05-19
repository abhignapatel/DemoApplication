package com.e.demoaplplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.demoaplplication.R;
import com.e.demoaplplication.adapter.FavoriteListAdapter;
import com.e.demoaplplication.bean.FavoriteList;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment  {
    private RecyclerView recyclerView;
    private FavoriteListAdapter favoriteListAdapter;
    private List<FavoriteList> favoriteList = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favorite_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        favoriteListAdapter = new FavoriteListAdapter(favoriteList,getContext());
        recyclerView.setAdapter(favoriteListAdapter);

       return view;
    }

    public void setFavoriteList(List<FavoriteList> favoriteList) {
        this.favoriteList = favoriteList;
        favoriteListAdapter.addData(this.favoriteList);
    }
}

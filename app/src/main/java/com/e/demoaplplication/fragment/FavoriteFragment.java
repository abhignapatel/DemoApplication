package com.e.demoaplplication.fragment;

import android.content.Intent;
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

import java.util.List;

public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    FavoriteListAdapter favoriteListAdapter;
    List<FavoriteList> favoriteList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
       favoriteListAdapter = new FavoriteListAdapter(favoriteList,getContext());
       recyclerView.setAdapter(favoriteListAdapter);

        Intent intent = getActivity().getIntent();

       return view;
    }
}

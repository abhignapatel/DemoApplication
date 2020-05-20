package com.e.demoaplplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.demoaplplication.R;
import com.e.demoaplplication.adapter.FavoriteListAdapter;
import com.e.demoaplplication.bean.FavoriteModel;
import com.e.demoaplplication.databinding.FavoriteFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment  {
    private RecyclerView recyclerView;
    private FavoriteListAdapter favoriteListAdapter;
    private List<FavoriteModel> favoriteModel = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FavoriteFragmentBinding favoriteFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.favorite_fragment,container,false);
        View view = favoriteFragmentBinding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        favoriteListAdapter = new FavoriteListAdapter(favoriteModel,getContext());
        recyclerView.setAdapter(favoriteListAdapter);

       return view;
    }

    public void setFavoriteModel(List<FavoriteModel> favoriteModel) {
        this.favoriteModel = favoriteModel;
        favoriteListAdapter.addData(this.favoriteModel);
    }
}

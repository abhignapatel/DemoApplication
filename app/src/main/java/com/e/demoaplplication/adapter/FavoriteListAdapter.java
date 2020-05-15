package com.e.demoaplplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.demoaplplication.R;
import com.e.demoaplplication.bean.FavoriteList;
import com.e.demoaplplication.bean.PostList;

import java.util.ArrayList;
import java.util.List;

import listener.FavClickListener;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {
    private List<FavoriteList> favoriteList;
    private Context context;



     public FavoriteListAdapter(List<FavoriteList> favoriteList, Context context){
       this.context= context;
      this.favoriteList = favoriteList;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoriteListAdapter.ViewHolder viewHolder, final int position) {
        Glide.with(context).load(favoriteList.get(position).getItemImage()).into(viewHolder.Image);
        viewHolder.Name.setText(favoriteList.get(position).getItemName());
        viewHolder.Login.setText(favoriteList.get(position).getItemLogin());
//        viewHolder.Icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i =  viewHolder.getAdapterPosition();
//                FavoriteList model = favoriteList.get(i);
//                favClickListener.onRemove(model);
//            }
      //  });

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView Name,Login;
       ImageView Image,Icon;
        public ViewHolder(@NonNull View view) {
            super(view);
            Name = view.findViewById(R.id.name);
            Login = view.findViewById(R.id.login);
            Image = view.findViewById(R.id.image);
            Icon = view.findViewById(R.id.icnstar);
        }
    }
}

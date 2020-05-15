package com.e.demoaplplication.adapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.e.demoaplplication.R;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.demoaplplication.bean.PostList;

import java.util.List;

import listener.FavClickListener;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<PostList> datalist;
    private Context context;
    private FavClickListener favClickListener;


    public SearchListAdapter(List<PostList> datalist, Context context, FavClickListener favClickListener) {
        this.datalist = datalist;
        this.context = context;
        this.favClickListener = favClickListener;
    }

    //    @Override
    public SearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
////        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"onclick"+String.valueOf(viewHolder.getAbsoluteAdapterPosition()),Toast.LENGTH_LONG).show();
//
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        Glide.with(context).load(datalist.get(position).getAvatarUrl()).into(viewHolder.Image);
        viewHolder.Name.setText(datalist.get(position).getName());
        viewHolder.Login.setText(datalist.get(position).getLogin());

        if (datalist.get(viewHolder.getAdapterPosition()).isFavorite()){
            viewHolder.Icon.setImageResource(android.R.drawable.star_big_on);
        } else {
            viewHolder.Icon.setImageResource(android.R.drawable.star_big_off);

        }

        viewHolder.Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i =  viewHolder.getAdapterPosition();
                PostList model = datalist.get(i);
                favClickListener.onFavClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, Login;
        ImageView Image,Icon;

        public ViewHolder(View view) {
            super(view);
            Icon = view.findViewById(R.id.icnstar);
            Image = view.findViewById(R.id.image);
            Name = view.findViewById(R.id.name);
            Login = view.findViewById(R.id.login);



        }

    }
    public void addFav(List<PostList> postLists) {
        datalist = postLists;
        notifyDataSetChanged();
    }

}
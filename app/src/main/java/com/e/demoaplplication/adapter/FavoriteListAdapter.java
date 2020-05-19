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
import java.util.List;



public class FavoriteListAdapter extends
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {
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
        Glide.with(context).load(favoriteList.get(position).getAvatarUrl()).into(viewHolder.Image);
        viewHolder.Name.setText(favoriteList.get(position).getName());
        viewHolder.Login.setText(favoriteList.get(position).getLogin());


//        viewHolder.Icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i =  viewHolder.getAdapterPosition();
//                FavoriteList model = favoriteList.get(i);
//                if(favClickListener.onRemove(model)){
//                    viewHolder.Icon.setImageResource(android.R.drawable.star_big_off);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public void addData(List<FavoriteList> favoriteList) {
        this.favoriteList = favoriteList;
        notifyDataSetChanged();
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
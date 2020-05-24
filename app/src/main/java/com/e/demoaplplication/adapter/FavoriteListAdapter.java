package com.e.demoaplplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.e.demoaplplication.R;
import com.e.demoaplplication.bean.FavoriteModel;
import com.e.demoaplplication.databinding.ItemListFavBinding;
import java.util.List;


public class FavoriteListAdapter extends
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>{
    private List<FavoriteModel> favoriteModel;
    private Context context;
    ItemListFavBinding binding;


    public FavoriteListAdapter(List<FavoriteModel> favoriteModel, Context context){
       this.context= context;
      this.favoriteModel = favoriteModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemListFavBinding.bind(inflater.inflate(R.layout.item_list_fav, parent, false));

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

     FavoriteModel favoriteModels = favoriteModel.get(position);
     viewHolder.itemListBinding.setFavoriteModel(favoriteModels);

     Glide.with(context).load(favoriteModel.get(viewHolder.getAdapterPosition()).getAvatarUrl()).into(viewHolder.itemListBinding.image);

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
        return favoriteModel.size();
    }

    public void addData(List<FavoriteModel> favoriteModel) {
        this.favoriteModel = favoriteModel;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListFavBinding itemListBinding;
        public ViewHolder(@NonNull ItemListFavBinding view) {
            super(view.getRoot());
            itemListBinding=view;

        }
    }

}

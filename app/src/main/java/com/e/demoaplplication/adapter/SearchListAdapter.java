package com.e.demoaplplication.adapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.e.demoaplplication.R;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.e.demoaplplication.bean.PostModel;
import com.e.demoaplplication.databinding.ItemListBinding;
import java.util.List;
import listener.FavClickListener;

public class SearchListAdapter extends
    RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<PostModel> postModels;
    private Context context;
    private FavClickListener favClickListener;
    private static final String TAG = "SearchListAdapter";
    ItemListBinding itemListBinding;


    public SearchListAdapter(List<PostModel> postModels, Context context, FavClickListener favClickListener) {
        this.postModels = postModels;
        this.context = context;
        this.favClickListener = favClickListener;
    }

    //    @Override
      public SearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

          itemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_list,parent,false);
          final ViewHolder viewHolder = new ViewHolder(itemListBinding);
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

        PostModel postModel = postModels.get(position);
       viewHolder.itemListBinding.setPostModel(postModel);

        Glide.with(context).load(postModels.get(viewHolder.getAdapterPosition()).getAvatarUrl())
            .into(viewHolder.itemListBinding.image);


        if (postModels.get(viewHolder.getAdapterPosition()).isFavorite()){
            viewHolder.itemListBinding.icnstar.setImageResource(android.R.drawable.star_big_on);
        } else {
            viewHolder.itemListBinding.icnstar.setImageResource(android.R.drawable.star_big_off);

        }
        viewHolder.itemListBinding.icnstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i =  viewHolder.getAdapterPosition();// search array list of index
                PostModel model = postModels.get(i);//post list object define the search array list index

                if (model.isFavorite()){
                    favClickListener.onRemove(model); //if already true than condition false here
                    model.setFavorite(false);
                }else{
                    favClickListener.onFavClick(model);
                    model.setFavorite(true);//change the fav model
                }
                postModels.set(i,model); // reset the change search data
                notifyDataSetChanged();//refresh view after changes


            }
        });
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ItemListBinding itemListBinding;

        public ViewHolder(ItemListBinding view) {
            super(view.getRoot());
            itemListBinding = view;
        }
    }

    public void addFav(List<PostModel> postModels) {
        this.postModels = postModels;
        notifyDataSetChanged();
    }

}
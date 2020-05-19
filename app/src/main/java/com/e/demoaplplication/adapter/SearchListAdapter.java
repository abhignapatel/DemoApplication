package com.e.demoaplplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.e.demoaplplication.R;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.e.demoaplplication.bean.PostModel;
import java.util.List;
import listener.FavClickListener;

public class SearchListAdapter extends
    RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<PostModel> postModels;
    private Context context;
    private FavClickListener favClickListener;
    private static final String TAG = "SearchListAdapter";


    public SearchListAdapter(List<PostModel> postModels, Context context, FavClickListener favClickListener) {
        this.postModels = postModels;
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

        Glide.with(context).load(postModels.get(position).getAvatarUrl()).into(viewHolder.Image);
        viewHolder.Name.setText(postModels.get(position).getName());
        viewHolder.Login.setText(postModels.get(position).getLogin());

        if (postModels.get(viewHolder.getAdapterPosition()).isFavorite()){
            viewHolder.Icon.setImageResource(android.R.drawable.star_big_on);
        } else {
            viewHolder.Icon.setImageResource(android.R.drawable.star_big_off);

        }
        viewHolder.Icon.setOnClickListener(new View.OnClickListener() {
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

    public void addFav(List<PostModel> postModels) {
        this.postModels = postModels;
        notifyDataSetChanged();
    }


}
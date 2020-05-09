package com.e.demoaplplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.e.demoaplplication.R;
import com.e.demoaplplication.bean.PostList;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private List<PostList> datalist;
    private ImageView Image;

    public PostAdapter(List<PostList> datalist,Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder ViewHolder, int position) {
//
        Glide.with(context).load(datalist.get(position).getAvatarUrl()).into(Image);
        ViewHolder.Name.setText(datalist.get(position).getName());
        ViewHolder.Login.setText(datalist.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Login;

        public ViewHolder(View view) {
            super(view);
            Image = view.findViewById(R.id.image);
            Name = view.findViewById(R.id.name);
            Login = view.findViewById(R.id.login);
        }
    }
}

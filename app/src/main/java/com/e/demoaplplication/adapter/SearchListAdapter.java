package com.e.demoaplplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.e.demoaplplication.R;
import com.e.demoaplplication.bean.PostList;
import java.util.List;


public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
private List<PostList> datalist;
        Context context;
private ImageView Image,Icon;


public SearchListAdapter(List<PostList> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
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
public void onBindViewHolder(SearchListAdapter.ViewHolder ViewHolder, final int position) {
//
        Glide.with(context).load(datalist.get(position).getAvatarUrl()).into(Image);
        ViewHolder.Name.setText(datalist.get(position).getName());
        ViewHolder.Login.setText(datalist.get(position).getLogin());
        Icon.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Toast.makeText(context,"Add to fav",Toast.LENGTH_LONG).show();
        }
        });

        }

@Override
public int getItemCount() {
        return datalist.size();
        }

class ViewHolder extends RecyclerView.ViewHolder  {
    TextView Name, Login;

    public ViewHolder(View view) {
        super(view);
        Icon = view.findViewById(R.id.icnstar);
        Image = view.findViewById(R.id.image);
        Name = view.findViewById(R.id.name);
        Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAbsoluteAdapterPosition();

            }
        });

    }

}


}
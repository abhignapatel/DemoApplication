package com.e.demoaplplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.demoaplplication.Api;
import com.e.demoaplplication.R;
import com.e.demoaplplication.adapter.SearchListAdapter;
import com.e.demoaplplication.bean.PostList;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment   {

    private List<PostList> datalist = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.search_fragment, container, false);

       recyclerView = view.findViewById(R.id.recyclerView);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
       recyclerView.setLayoutManager(layoutManager);
       editText = view.findViewById(R.id.searchedit);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

                    boolean repeat = false;
                    for (PostList i : datalist) {
                        if (i.getLogin().equals(editText.getText().toString())) {
                            Toast.makeText(getContext(), "repeat data", Toast.LENGTH_LONG).show();
                            repeat = true;
                        }
                    }
                    if (!repeat) {
                        initView();
                    }
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    private void initView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api  api = retrofit.create(Api.class);
        Call<PostList> call = api.getPostList(editText.getText().toString());
        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {

                datalist.add(response.body());
                editText.setText("");
                RecyclerView.Adapter adapter = new SearchListAdapter(datalist,getContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

}

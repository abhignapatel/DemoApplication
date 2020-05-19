package com.e.demoaplplication.fragment;

import android.content.Context;
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
import com.e.demoaplplication.bean.PostModel;

import java.util.ArrayList;
import java.util.List;

import database.FavDataBase;
import listener.FavClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment implements FavClickListener {

    private List<PostModel> postModels = new ArrayList<>();
    private EditText editText;
    private SearchListAdapter adapter;
    private FavDataBase favDataBase;
    private static final String TAG = "SearchFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SearchListAdapter(postModels, getContext(), this);
        recyclerView.setAdapter(adapter);

        favDataBase = new FavDataBase(getContext());
       List<PostModel> allData = favDataBase.getAllData();
//        Toast.makeText(getContext(),"get data" + allData ,Toast.LENGTH_LONG).show();
        Log.e(TAG, "get data" + allData);
        editText = view.findViewById(R.id.searchedit);


        //show the search btn on keyboard
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);//hide keyboard after search

                    boolean repeat = false;// don't repeat data
                    for (PostModel i : postModels) {
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
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        Api api = retrofit.create(Api.class);
        Call<PostModel> call = api.getPostList(editText.getText().toString());
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(@NonNull Call<PostModel> call, @NonNull Response<PostModel> response) {

                Log.e(TAG, response.toString());

                PostModel body = response.body();
                String login = body.getLogin();
                boolean available = favDataBase.isThisLoginAvailable(login);
                body.setFavorite(available);
                //is this login available in fav db
                postModels.add(body);
                editText.setText("");
                adapter.addFav(postModels);//send the data to adapter

            }

            @Override
            public void onFailure(@NonNull Call<PostModel> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    @Override
    public void onFavClick(PostModel model) {
        favDataBase.addFavData(model);
    }

    @Override
    public void onRemove(PostModel model) {
        favDataBase.removeFavData(model);
    }

}
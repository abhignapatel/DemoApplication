package com.e.demoaplplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.demoaplplication.adapter.PostAdapter;
import com.e.demoaplplication.bean.PostList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private List<PostList> datalist = new ArrayList<>();
    EditText editText;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        editText = findViewById(R.id.searchedit);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    boolean repeat = false;
                    for(PostList i :datalist){
                        if (i.getLogin().equals(editText.getText().toString())){
                            Toast.makeText(getApplicationContext(),"repeat data",Toast.LENGTH_LONG).show();
                            repeat =true;
                        }
                    }
                    if(!repeat){
                        initView();
                    }
                    return true;
                }
                return false;
            }
        });
    }
    //    String s ="https://api.github.com/users/jaydonga";
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
                RecyclerView.Adapter adapter = new PostAdapter(datalist,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}


package com.e.demoaplplication;

import com.e.demoaplplication.bean.PostList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    String BASE_URL ="https://api.github.com";
    @GET("/users/{name}")
    Call<PostList> getPostList(@Path("name")String name);


}

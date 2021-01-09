package com.kanagalatech.newsapp.Util;

import com.kanagalatech.newsapp.Response.NewsList;
import com.kanagalatech.newsapp.Response.Result;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("home.json?")
    Call<NewsList> news_list(@Query("api-key") String apikey);
}

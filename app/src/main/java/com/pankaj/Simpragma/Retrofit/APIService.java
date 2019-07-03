package com.pankaj.Simpragma.Retrofit;

import com.pankaj.Simpragma.Model.SearchModel;

import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Query;


public interface APIService {


    @GET("/search")
    Call<SearchModel> getData(@Query("q")String q,
                              @Query("app_id")String app_id,
                              @Query("app_key")String app_key);


}

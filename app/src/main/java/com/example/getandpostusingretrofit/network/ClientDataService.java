package com.example.getandpostusingretrofit.network;

import com.example.getandpostusingretrofit.model.ModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientDataService {

    @GET("/jsons/{path_var}/retrofit.json")
    Call<List<ModelClass>> getAll(@Path("path_var") String pathVar);

    @POST("/Jsons/jsonforretrofitimplementation.json")
    Call<List<ModelClass>> setAll();

}

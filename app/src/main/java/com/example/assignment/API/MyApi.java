package com.example.assignment.API;

import com.example.assignment.model.foodModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyApi {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
   MyApi MY_API = new Retrofit.Builder()
           .baseUrl("http://10.24.3.236:8080/")
           .addConverterFactory(GsonConverterFactory.create(gson))
           .build()
           .create(MyApi.class);

    @GET("trangchu")
    Call<List<foodModel>> getData();
    @GET("getone/{id}")
    Call<Void> getoneProduct(@Path("id") String productId);
    @POST("addfood")
    Call<foodModel> createUser(@Body foodModel model);

    @PUT("edit/{id}")
    Call<foodModel> update(@Path("id") String id, @Body foodModel model);


    @DELETE("/{id}")
    Call<Void> deleteUser(@Path("id") String userId);

}


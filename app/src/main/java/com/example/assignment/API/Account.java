package com.example.assignment.API;

import com.example.assignment.model.AcountModel;
import com.example.assignment.model.MessengerModel;
import com.example.assignment.model.foodModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Account {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    Account acountservice = new Retrofit.Builder()
            .baseUrl("http://192.168.169.102:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Account.class);

    @GET("user/getall")
    Call<List<AcountModel>> getAllUser();

    @POST("user/resgiter")
    Call<AcountModel> registerUser(@Body AcountModel model);


    @POST("user/login")
    Call<AcountModel> loginUser(@Body AcountModel model);
    @POST("/messenger/message")
    Call<Void> sendMessage(@Body MessengerModel messengerModel);


}

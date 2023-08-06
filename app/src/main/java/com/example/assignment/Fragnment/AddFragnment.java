package com.example.assignment.Fragnment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.API.MyApi;
import com.example.assignment.R;
import com.example.assignment.model.foodModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddFragnment extends Fragment {

    EditText ediname, edtgia, edilinkanh, editmota;

    Button buttonadd;

    public AddFragnment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_fragnment, container, false);
        ediname = view.findViewById(R.id.edtnamesp);
        edtgia = view.findViewById(R.id.edtdongia);
        edilinkanh = view.findViewById(R.id.edtimage);
        editmota = view.findViewById(R.id.edtdescription);
        buttonadd = view.findViewById(R.id.btnaddproduct);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ediname.getText().toString().trim();
                String gia = edtgia.getText().toString().trim();
                String link = edilinkanh.getText().toString().trim();
                String mota = editmota.getText().toString().trim();

                if(name.isEmpty()){
                    Toast.makeText(getContext(), "không được  để trống tên sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(gia.isEmpty()){
                    Toast.makeText(getContext(), "không được  để trống  giá sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(link.isEmpty()){
                    Toast.makeText(getContext(), "không được  để trống link ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mota.isEmpty()){
                    Toast.makeText(getContext(), "không được  để trống mô tả", Toast.LENGTH_SHORT).show();
                    return;
                }

            double giacheck = 0 ;
             try {
                 giacheck = Double.parseDouble(gia);
             }catch (Exception e){
                 Toast.makeText(getContext(), "nhâ giá bằng số", Toast.LENGTH_SHORT).show();
                 return;
             }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.169.102:8080/") // Thay thế bằng URL của MongoDB API
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MyApi userService = retrofit.create(MyApi.class);
                foodModel food = new foodModel();
                food.setNamesp(name);
                food.setDongia(Double.valueOf(giacheck));
                food.setImage(link);
                food.setDescription(mota);

                Call<foodModel> call = userService.createUser(food);
                call.enqueue(new Callback<com.example.assignment.model.foodModel>() {
                    @Override
                    public void onResponse(Call<com.example.assignment.model.foodModel> call, Response<com.example.assignment.model.foodModel> response) {
                        if (response.isSuccessful()) {
                            foodModel model = response.body();
                            Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.e("MainActivity", "Response unsuccessful: " + response.message());
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<com.example.assignment.model.foodModel> call, Throwable t) {

                    }
                });
            }
        });


        return view;


    }



}
package com.example.assignment.Fragnment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.API.MyApi;
import com.example.assignment.Adapter.FoodAdapter;
import com.example.assignment.R;
import com.example.assignment.model.foodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragnmentHome extends Fragment {
    RecyclerView recyclerView;
    FoodAdapter adapter;
    private TextView txt ;

    ArrayList<foodModel> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    public FragnmentHome() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragnment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleviewHome);
        txt = view.findViewById(R.id.txtketqua);
         adapter = new FoodAdapter(getContext() , list);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        // Sử dụng Retrofit để gọi API và lấy dữ liệu
        // Sử dụng Retrofit để gọi API và lấy dữ liệu
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.24.3.236:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi apiService = retrofit.create(MyApi.class);
        Call<List<foodModel>> call = apiService.getData();
        call.enqueue(new Callback<List<foodModel>>() {


            @Override
            public void onResponse(Call<List<foodModel>> call, Response<List<foodModel>> response) {

                if (response.isSuccessful()) {
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();

                } else {
                    Log.i("Sync","Fail");
                }
            }

            @Override
            public void onFailure(Call<List<foodModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
            return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.24.3.236:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi apiService = retrofit.create(MyApi.class);
        Call<List<foodModel>> call = apiService.getData();
        call.enqueue(new Callback<List<foodModel>>() {


            @Override
            public void onResponse(Call<List<foodModel>> call, Response<List<foodModel>> response) {

                if (response.isSuccessful()) {
                    list = (ArrayList<foodModel>) response.body();
                    Log.i("list", "onResponse: "+list);
                    FoodAdapter myAdapter = new FoodAdapter(getContext() , list);
                    recyclerView.setAdapter(myAdapter);
                } else {
                    Log.i("Sync","Fail");
                }
            }

            @Override
            public void onFailure(Call<List<foodModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
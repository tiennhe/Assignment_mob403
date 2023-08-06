package com.example.assignment.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.API.Account;
import com.example.assignment.R;
import com.example.assignment.model.AcountModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
EditText edtusernameres  , edtpassres ;
Button btnsignup ;

    Account acount ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtpassres = findViewById(R.id.edtpasssingup) ;
        edtusernameres = findViewById(R.id.edtemailsignup) ;
        btnsignup = findViewById(R.id.btnsingup) ;
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }


    public void addUser(){
        String username  = edtusernameres.getText().toString().trim() ;
        String pass = edtpassres.getText().toString().trim();
        if(username.isEmpty() ){
            Toast.makeText(this, "không được để trống username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty() ){
            Toast.makeText(this, "không được để trống pass", Toast.LENGTH_SHORT).show();
            return;
        }
        AcountModel acountModel = new AcountModel(username , pass) ;

        Call<AcountModel> call = Account.acountservice.registerUser(acountModel);

        call.enqueue(new Callback<AcountModel>() {
            @Override
            public void onResponse(Call<AcountModel> call, Response<AcountModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "đăng kí thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "đăng kí fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcountModel> call, Throwable t) {

            }
        });







    }
}
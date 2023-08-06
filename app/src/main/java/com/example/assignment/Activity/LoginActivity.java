package com.example.assignment.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.API.Account;
import com.example.assignment.Fragnment.ChatFragnment;
import com.example.assignment.MainActivity;
import com.example.assignment.R;
import com.example.assignment.model.AcountModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
TextView txtnewAcount , txtforgotpass ;
EditText edtusername , edtpass ;
Button btnlogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtnewAcount = findViewById(R.id.txtdangki);
        txtforgotpass = findViewById(R.id.forgotpass);

        edtpass = findViewById(R.id.edtpasssingin) ;
        edtusername = findViewById(R.id.edtemailsignin) ;

        btnlogin = findViewById(R.id.btnsingin) ;
        txtnewAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login(){


        String username = edtusername.getText().toString().trim() ;
        String pass = edtpass.getText().toString().trim();

        if(username.isEmpty() ){
            Toast.makeText(this, "không được để trống username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty() ){
            Toast.makeText(this, "không được để trống pass", Toast.LENGTH_SHORT).show();
            return;
        }
        AcountModel acountModel = new AcountModel(username , pass) ;

        Call<AcountModel> call = Account.acountservice.loginUser(acountModel);

        call.enqueue(new Callback<AcountModel>() {
            @Override
            public void onResponse(Call<AcountModel> call, Response<AcountModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);

                    intent.putExtra("username" , username);

                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "đăng nhập lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcountModel> call, Throwable t) {

                Log.d("err", "onFailure: "+t);

            }
        });


    }
}
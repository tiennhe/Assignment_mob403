package com.example.assignment.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.API.Account;
import com.example.assignment.API.MyApi;
import com.example.assignment.Fragnment.FragnmentHome;
import com.example.assignment.R;
import com.example.assignment.model.AcountModel;
import com.example.assignment.model.foodModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowDetailActivity extends AppCompatActivity {
    private foodModel model;
    private ImageView imageView, imgdelete, imgupdate;
    private TextView name, gia, description;
    private   String id , nameget ,descriptionget ,ImgUrl;
    private   double giaget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        imgdelete = findViewById(R.id.imgdeleteshowdetail);
        imgupdate = findViewById(R.id.imgeditshowdetail);
        imageView = findViewById(R.id.img_showdetail);
        name = findViewById(R.id.name_showdetail);
        gia = findViewById(R.id.gia_showdetail);
        description = findViewById(R.id.description_showdetail);


        Intent intent = getIntent();
         id = intent.getStringExtra("id");
         nameget = intent.getStringExtra("name");

         giaget = intent.getDoubleExtra("gia", 0);
         descriptionget = intent.getStringExtra("description");
         ImgUrl = intent.getStringExtra("EXTRA_IMAGE_URL");


        Call<Void> call = MyApi.MY_API.getoneProduct(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    name.setText("Tên Sản Phẩm" +nameget);

                    description.setText("Mô tả"+descriptionget);
                    gia.setText(String.valueOf("Gía" +giaget));
                    Picasso.get().load(ImgUrl).into(imageView);
                    imgdelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        delete();
                        }
                    });
                    imgupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ubdate();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
public void delete(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Xóa sản phẩm");
    builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");
    builder.setPositiveButton("Xóa", (dialog, which) -> {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.24.3.236:8080/") // Thay thế bằng URL của MongoDB API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi userService = retrofit.create(MyApi.class);


        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();// Thay thế bằng id của đối tượng cần xóa

        Call<Void> call = userService.deleteUser(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xử lý khi đối tượng đã được xóa thành công
                    Toast.makeText(ShowDetailActivity.this, "thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShowDetailActivity.this , FragnmentHome.class) ;
                    startActivity(intent);

                } else {
                    // Xử lý khi có lỗi xảy ra
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu
            }
        });

    });
    builder.setNegativeButton("Hủy", null);
    AlertDialog dialog = builder.create();
    dialog.show();
}


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    public void ubdate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.layout_edit_food, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        dialog.show();
        EditText edtname = dialog.findViewById(R.id.tenspedit);
        EditText edtgia = dialog.findViewById(R.id.dongiaedit);
        EditText edtlinkanh = dialog.findViewById(R.id.imagedit);
        EditText edimota = dialog.findViewById(R.id.descriptionedit);


        edtname.setText(nameget);
        edtgia.setText(String.valueOf(giaget));
        edtlinkanh.setText(ImgUrl);
        edimota.setText(descriptionget);


        Button btnEdit = dialog.findViewById(R.id.btnedit);
        Button btnCancel = dialog.findViewById(R.id.btnexit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String name1 = edtname.getText().toString().trim();
                String gia1 = edtgia.getText().toString().trim();
                String link1 = edtlinkanh.getText().toString().trim();
                String mota1 = edimota.getText().toString().trim();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.24.3.236:8080/") // Thay thế bằng URL của MongoDB API
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MyApi userService = retrofit.create(MyApi.class);

                foodModel food = new foodModel();
                food.setNamesp(name1);
                food.setDongia(Double.valueOf(gia1));
                food.setImage(link1);
                food.setDescription(mota1);
                Call<foodModel> call = userService.update(id , food);
                call.enqueue(new Callback<foodModel>() {
                    @Override
                    public void onResponse(Call<foodModel> call, Response<foodModel> response) {
                        name.setText("Tên Sản Phẩm: " +response);


                        description.setText("Mô tả: "+mota1);
                        gia.setText(String.valueOf("Gía sản Phẩm" +gia1));
                        Picasso.get().load(link1).into(imageView);
                        Toast.makeText(ShowDetailActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                    @Override
                    public void onFailure(Call<foodModel> call, Throwable t) {

                    }
                });




            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


}
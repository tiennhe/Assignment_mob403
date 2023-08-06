package com.example.assignment.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.API.MyApi;
import com.example.assignment.Activity.LoginActivity;
import com.example.assignment.Activity.ShowDetailActivity;
import com.example.assignment.Fragnment.FragnmentHome;
import com.example.assignment.R;
import com.example.assignment.model.foodModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    private Context context ;
    private Picasso mPicasso;
    FragnmentHome fragnmentHome  = new FragnmentHome() ;
    private ArrayList<foodModel> photos ;
    public FoodAdapter(List<foodModel> objects) {
        photos = (ArrayList<foodModel>) objects;

        notifyDataSetChanged();

    }
    private List<foodModel> list ;

    public FoodAdapter(Context context, List<foodModel> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food , parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        foodModel model =list.get(position);
        if(model==null){
            return;
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ShowDetailActivity.class);
                intent.putExtra("id" , model.getId());
                intent.putExtra("name" , model.getNamesp());
                intent.putExtra("gia" , model.getDongia());
                intent.putExtra("description" , model.getDescription());
                intent.putExtra("EXTRA_IMAGE_URL",  model.getImage());
                context.startActivity(intent);
            }
        });
        foodModel objects = list.get(position);
       Picasso.get().load(objects.getImage()).into(holder.imageView);

        holder.name.setText("Tên Sản Phẩm: "+model.getNamesp());

        holder.gia.setText("Gía sản Phẩm: "+String.valueOf(model.getDongia()));




    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView  ;
        private TextView name , gia  ;

        private LinearLayout linearLayout ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_food);
            name = itemView.findViewById(R.id.name_sanpham);
            gia = itemView.findViewById(R.id.gia_sanpham);

            linearLayout = itemView.findViewById(R.id.layoutitem);
        }
    };


}

package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.model.MessengerModel;
import com.example.assignment.model.foodModel;

import java.util.ArrayList;
import java.util.List;

public class MessengerApdater extends RecyclerView.Adapter<MessengerApdater.ViewHolder>{
 private Context context ;
 private ArrayList<MessengerModel> arrayList  =new ArrayList<>();

    public MessengerApdater(Context context, ArrayList<MessengerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    // Constructor và các phương thức khác của Adapter

    public void addMessage(MessengerModel message) {
        arrayList.add(message);
        notifyItemInserted(arrayList.size() - 1);
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemmessenger , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessengerModel model =arrayList.get(position);
        if(model==null){
            return;
        }

        holder.txtmessenger.setText(model.getId_User());
        holder.txtussername.setText(model.getMessenger());


    }

    @Override
    public int getItemCount() {
        if(arrayList!=null){
            return arrayList.size();
        }
        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtussername ;
        private TextView txtmessenger ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtussername = itemView.findViewById(R.id.txtusernamehienthi) ;
            txtmessenger = itemView.findViewById(R.id.txtmessenger);
        }
    }
}

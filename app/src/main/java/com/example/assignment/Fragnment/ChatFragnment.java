package com.example.assignment.Fragnment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import com.example.assignment.API.Account;
import com.example.assignment.Adapter.MessengerApdater;
import com.example.assignment.R;
import com.example.assignment.model.MessengerModel;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatFragnment extends Fragment {

private EditText edtmessenger;
private Button btnsend ;
private String nameuser;

private RecyclerView recyclerView ;
private MessengerApdater apdater ;
private ArrayList<MessengerModel> arrayList = new ArrayList<>() ;
    private Socket socket;

    public ChatFragnment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_fragnment, container, false);
        // Inflate the layout for this fragment

        edtmessenger = view.findViewById(R.id.edtmessenger) ;
        btnsend = view.findViewById(R.id.sendButton);
        recyclerView = view.findViewById(R.id.recyclerViewmesenger) ;
        apdater = new MessengerApdater(getContext() , arrayList) ;
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(apdater);

        Handler mainHandler = new Handler(Looper.getMainLooper());
        try {
            Socket socket = IO.socket("http://10.24.3.236:8080/");
            socket.connect();
            socket.on("chat message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject data = (JSONObject) args[0];
                    String content = data.optString("messenger");
                    MessengerModel message = new MessengerModel(content);
                    // Sử dụng Handler để chuyển tác vụ cập nhật giao diện người dùng tới luồng giao diện
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                       apdater.addMessage(message);
                        }
                    });
                }
            });

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmessenger();
            }
        });
        return view;
    }

    public void sendmessenger(){
        String messenger = edtmessenger.getText().toString().trim() ;


        MessengerModel model = new MessengerModel(messenger);

        Call<Void> call = Account.acountservice.sendMessage(model) ;

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "send thành công", Toast.LENGTH_SHORT).show();
                edtmessenger.setText("");

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });



    }

    public void getmessenger(){

    }

}
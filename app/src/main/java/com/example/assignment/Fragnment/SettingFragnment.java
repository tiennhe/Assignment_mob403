package com.example.assignment.Fragnment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.assignment.Activity.LoginActivity;
import com.example.assignment.R;



public class SettingFragnment extends Fragment {

private Button btndangxuat ;

    public SettingFragnment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_fragnment, container, false);

        btndangxuat = view.findViewById(R.id.btndangxuat);
        // Inflate the layout for this fragment
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , LoginActivity.class);
                startActivity(intent);

            }
        });

        return view;



    }

}
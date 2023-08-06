package com.example.assignment.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.assignment.Fragnment.AddFragnment;
import com.example.assignment.Fragnment.ChatFragnment;
import com.example.assignment.Fragnment.FragnmentHome;
import com.example.assignment.Fragnment.SettingFragnment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragnmentHome();
            case 1:
                return new AddFragnment();
            case 2:
                return new ChatFragnment();
            case 3:
                return new SettingFragnment();

            default:
                return new FragnmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

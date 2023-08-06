package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.assignment.Adapter.ViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

        private ViewPager viewPager ;
        private BottomNavigationView view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        view = findViewById(R.id.bottomnavigation);

        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        view.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        view.getMenu().findItem(R.id.menu_favorite).setChecked(true);
                        break;
                    case 2:
                        view.getMenu().findItem(R.id.chat).setChecked(true);
                        return;
                    case 3:
                        view.getMenu().findItem(R.id.menu_setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.menu_favorite) {
                    viewPager.setCurrentItem(1);
                }
                else if(itemId==R.id.chat){
                    viewPager.setCurrentItem(2);
                }
                else if (itemId == R.id.menu_setting) {
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }

}
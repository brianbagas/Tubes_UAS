package com.example.tubes_uts_e_2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.fragment.FirstFragment;
import com.example.tubes_uts_e_2.fragment.SecondFragment;
import com.example.tubes_uts_e_2.fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        changeFragment(new FirstFragment());
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.first_menu:
                changeFragment(new FirstFragment());
                return true;

            case R.id.second_menu:
                changeFragment(new SecondFragment());
                return true;

            case R.id.third_menu:
                changeFragment(new ThirdFragment());
                return true;
        }
        return false;
    }

    //  Method untuk mengubah fragment
    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_home, fragment)
                .commit();
    }
}
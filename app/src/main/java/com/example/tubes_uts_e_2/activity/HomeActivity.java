package com.example.tubes_uts_e_2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.fragment.FirstFragment;
import com.example.tubes_uts_e_2.fragment.SecondFragment;
import com.example.tubes_uts_e_2.fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    BottomNavigationView bottomNavigationView;
    ImageView btnProfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(getIntent().getBooleanExtra("order", false)) {
            changeFragment(new ThirdFragment());
        } else {
            changeFragment(new FirstFragment());
        }
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        btnProfil = findViewById(R.id.btnProfil);
        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilActivity = new Intent(HomeActivity.this, ProfilActivity.class);
                startActivity(profilActivity);
            }
        });
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
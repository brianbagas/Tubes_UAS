package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.databinding.ActivityLoginBinding;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private UserPreferences userPreferences;

    private User user;
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //init
        getSupportActionBar().hide();
        user = new User();
        activityLoginBinding.setLogin(user);
        activityLoginBinding.setActivity(this);
        activityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                homeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeActivity);
            }
        });

        activityLoginBinding.tvClickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                registerActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(registerActivity);
            }
        });

    }
}
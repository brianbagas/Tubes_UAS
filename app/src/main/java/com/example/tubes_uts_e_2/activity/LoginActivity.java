package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Query;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.databinding.ActivityLoginBinding;
import com.example.tubes_uts_e_2.db.DatabaseUser;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private UserPreferences userPreferences;

    private User user;
    String userInput;
    String passInput;
    List<User> loginInfo;
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
                if(validateForm()) {
                    userInput = activityLoginBinding.etUsername.getEditText().getText().toString().trim();
                    passInput = activityLoginBinding.etPassword.getEditText().getText().toString().trim();
                    loginInfo = getLoginInfo(userInput);

                    if( userInput.equals(loginInfo.get(0).getUsername()) && passInput.equals(loginInfo.get(0).getPassword()) ) {
                        //set userPreferences
                        userPreferences.setLogin(userInput,passInput);
                        checkLogin();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show();
                    }
                }
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

    private boolean validateForm() {
        //check empty
        if(activityLoginBinding.etUsername.getEditText().getText().toString().isEmpty() || activityLoginBinding.etPassword.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Username atau Password Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkLogin() {
        if(userPreferences.checkLogin()) {
            Intent homeActivity = new Intent(LoginActivity.this, HomeActivity.class);
            homeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeActivity);
            finish();
        }
    }



    private List<User> getLoginInfo(String username) {

        List<User> userList = DatabaseUser.getInstance(LoginActivity.this)
                .getDatabase()
                .userDao()
                .findUser(username);

        return userList;
    }
}
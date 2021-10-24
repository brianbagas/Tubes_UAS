package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.db.DatabaseUser;
import com.example.tubes_uts_e_2.model.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNama, etEmail, etUsername, etPassword;
    private Button btnRegister;
    List<User> userList;
    User temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(cekKosong()) {
                    temp = new User(0, nama, email, username, password, null);
                    AddUser(temp);
                    Intent loginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    loginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginActivity);
                    finish();
                }
            }
        });
    }

    private void AddUser(User temp) {
        class addUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setNama(temp.getNama());
                user.setEmail(temp.getEmail());
                user.setUsername(temp.getUsername());
                user.setPassword(temp.getPassword());

                DatabaseUser.getInstance(RegisterActivity.this)
                        .getDatabase()
                        .userDao()
                        .insertUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(RegisterActivity.this, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show();;
            }
        }
        addUser add = new addUser();
        add.execute();
    }

    private boolean cekKosong() {
        if(etNama.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
            return false;
        } else {
            return true;
        }
    }
}
package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.db.DatabaseUser;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;

import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    private TextView tvNama, tvEmail;
    private Button btnLogout, btnDelete,btnEdit;
    ImageView btnBackHome;
    private UserPreferences userPreferences;
    private AlertDialog.Builder builder;

    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        userPreferences = new UserPreferences(this);

        tvNama = findViewById(R.id.tvProfilNama);
        tvEmail = findViewById(R.id.tvProfilEmail);
        userList = getUser(userPreferences.getUserLogin().getUsername());
        tvNama.setText(userList.get(0).getNama());
        tvEmail.setText(userList.get(0).getEmail());
        btnLogout = findViewById(R.id.btnLogout);
        btnDelete = findViewById(R.id.btnDelete);
        btnBackHome = findViewById(R.id.btnBackHome);
        btnEdit = findViewById(R.id.btnEdit);
        getSupportActionBar().hide();

        userPreferences = new UserPreferences(this);
        builder=new AlertDialog.Builder(ProfilActivity.this);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(ProfilActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editActivity = new Intent(ProfilActivity.this, EditActivity.class);
                startActivity(editActivity);
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(ProfilActivity.this, "Terima Kasih", Toast.LENGTH_SHORT).show();
                checkLogin();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Setting message manually and performing action on button click
                builder.setMessage("Apakah anda yakin ingin menghapus akun?").setTitle("Hapus Akun")
                        .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userList = getUser(userPreferences.getUserLogin().getUsername());
                        deleteUser(userList.get(0));
                        Toast.makeText(getApplicationContext(),"Hapus akun Berhasil!",
                                Toast.LENGTH_SHORT).show();
                        userPreferences.logout();
                        checkLogin();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Hapus akun dibatalkan",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                //Creating dialog box
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }

    private void checkLogin() {
        //akan mengecek login
        if(!userPreferences.checkLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Selamat Datang!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUser(User user) {
        class DeleteUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseUser.getInstance(ProfilActivity.this)
                        .getDatabase()
                        .userDao()
                        .deleteUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(ProfilActivity.this, "Akun terhapus", Toast.LENGTH_SHORT).show();
            }
        }
        DeleteUser delete = new DeleteUser();
        delete.execute();
    }

    private List<User> getUser(String username) {

        List<User> userList = DatabaseUser.getInstance(this)
                .getDatabase()
                .userDao()
                .findUser(username);

        return userList;
    }
}
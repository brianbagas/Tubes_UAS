package com.example.tubes_uts_e_2.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.db.DatabaseUser;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;

import java.io.InputStream;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_PICTURE = 1;
    private Bitmap bitmap = null;
    private Button btnSaveEdit;
    private ImageView btnCam;
    private EditText etNama, etEmail, etUsername, etPassword;
    private ImageView pp;
    private UserPreferences userPreferences;
    private List<User> userList;
    private AlertDialog.Builder builder;
    private User temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        userPreferences = new UserPreferences(this);

        etNama = findViewById(R.id.etNama);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSaveEdit = findViewById(R.id.btnSaveEdit);
        pp = findViewById(R.id.iv_gambar);
        userList = getUser(userPreferences.getUserLogin().getUsername());
        etNama.setText(userList.get(0).getNama());
        etEmail.setText(userList.get(0).getEmail());
        etUsername.setText(userList.get(0).getUsername());


        btnCam = findViewById(R.id.btnCam);

        userPreferences = new UserPreferences(this);
        builder=new AlertDialog.Builder(EditActivity.this);


        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_DENIED) {
                    String[] permission = {Manifest.permission.CAMERA};
                    requestPermissions(permission, PERMISSION_REQUEST_CAMERA);
                } else {
                    // Membuka kamera
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST);
                }

            }
        });

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(cekKosong()) {
                    temp = new User(0, nama, email, username, password, null);
                    AddUser(temp);
                    Intent profilActivity = new Intent(EditActivity.this, ProfilActivity.class);
                    profilActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(profilActivity);
                    finish();
                }
            }
        });

    }

    private List<User> getUser(String username) {

        List<User> userList = DatabaseUser.getInstance(this)
                .getDatabase()
                .userDao()
                .findUser(username);

        return userList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;

        if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            Uri selectedImage = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Toast.makeText(EditActivity.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            bitmap = (Bitmap) data.getExtras().get("data");
        }

        bitmap = getResizedBitmap(bitmap, 512);
        pp.setImageBitmap(bitmap);
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int maxSize) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
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
                user.setImgURL(temp.getImgURL());

                DatabaseUser.getInstance(EditActivity.this)
                        .getDatabase()
                        .userDao()
                        .updateUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(EditActivity.this, "Berhasil Edit data", Toast.LENGTH_SHORT).show();;
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
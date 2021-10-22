package com.example.tubes_uts_e_2.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tubes_uts_e_2.model.User;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //Definisi Key
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public UserPreferences(Context context) {
        this.context = context;
        //Penamaan preferences bebas
        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String username, String password) {
        //menyimpan data login ke dalam sharedPreferences
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);

        //commit
        editor.commit();
    }

    public User getUserLogin() {
        String username, password;

        username = sharedPreferences.getString(KEY_USERNAME, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);

        return new User(username, password);
    }

    public boolean checkLogin() {
        //mengembalikan nilai is_login
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logout() {
        //clear data pada sharedPreferences
        editor.clear();
        //commit
        editor.commit();
    }
}

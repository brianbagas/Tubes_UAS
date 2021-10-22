package com.example.tubes_uts_e_2.db;

import android.content.Context;

import androidx.room.Room;

public class DatabaseTicket {

    private Context context;
    private static DatabaseTicket databaseTicket;

    private AppDatabase database;

    public DatabaseTicket(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, "ticket").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseTicket getInstance(Context context){
        if(databaseTicket == null){
            databaseTicket = new DatabaseTicket(context);
        }
        return databaseTicket;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}

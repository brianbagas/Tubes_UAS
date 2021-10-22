package com.example.tubes_uts_e_2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tubes_uts_e_2.db.dao.TicketDao;
import com.example.tubes_uts_e_2.db.dao.UserDao;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.User;

@Database(entities = {Ticket.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TicketDao ticketDao();
    public abstract UserDao userDao();
}

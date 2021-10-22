package com.example.tubes_uts_e_2.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tubes_uts_e_2.model.Ticket;

import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM tickets")
    List<Ticket> getAll();

    @Insert
    void insertTicket(Ticket ticket);

    @Update
    void updateTicket(Ticket ticket);

    @Delete
    void deleteTicket(Ticket ticket);
}

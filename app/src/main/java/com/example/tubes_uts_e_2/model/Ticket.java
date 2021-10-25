package com.example.tubes_uts_e_2.model;

import androidx.databinding.BaseObservable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets")
public class Ticket extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user")
    private String user;
    @ColumnInfo(name = "judul")
    public String judul;
    @ColumnInfo(name = "tempat")
    public String tempat;
    @ColumnInfo(name = "tglnonton")
    public String tanggal;
    @ColumnInfo(name = "waktu")
    public String waktu;
    @ColumnInfo(name = "jenis")
    public String jenis;
    @ColumnInfo(name = "total")
    public int total;

    public Ticket() {

    }

    public Ticket(int id, String user, String judul, String tempat, String tanggal, String waktu, String jenis, int total) {
        this.id = id;
        this.user = user;
        this.judul = judul;
        this.tempat = tempat;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.jenis = jenis;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

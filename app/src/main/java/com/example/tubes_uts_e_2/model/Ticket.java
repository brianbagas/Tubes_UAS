package com.example.tubes_uts_e_2.model;

import androidx.databinding.BaseObservable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets")
public class Ticket extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "judulTicket")
    public String judulTicket;
    @ColumnInfo(name = "tempat")
    public String tempat;
    @ColumnInfo(name = "tglnonton")
    public String tglnonton;
    @ColumnInfo(name = "waktu")
    public int waktu;
    @ColumnInfo(name = "jenis")
    public String jenis;
    @ColumnInfo(name = "total")
    public int total;

    public Ticket(String judulTicket, String tempat, String tglnonton, int waktu, String jenis, int total) {
        this.judulTicket = judulTicket;
        this.tempat = tempat;
        this.tglnonton = tglnonton;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJudul() {
        return judulTicket;
    }

    public void setJudul(String judulTicket) {
        this.judulTicket = judulTicket;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTglnonton() {
        return tglnonton;
    }

    public void setTglnonton(String tglnonton) {
        this.tglnonton = tglnonton;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
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

    public static Ticket[] listoftiket={
            new Ticket("Spiderman","Amplaz","1-10-2021",2020,"2D",1),
    };
}

package com.example.tubes_uts_e_2.model;

import com.example.tubes_uts_e_2.R;

public class Movie {
    private String judul;
    private int gambar;

    public Movie(String judul, int gambar) {
        this.judul = judul;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public static Movie[] listofMovie={
            new Movie("Spiderman", R.drawable.spider),
            new Movie("One Piece", R.drawable.onepiece)
    };
}

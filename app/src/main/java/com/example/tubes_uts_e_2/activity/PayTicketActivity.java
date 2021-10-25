package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.model.Movie;
import com.example.tubes_uts_e_2.model.Ticket;
import com.google.gson.Gson;

public class PayTicketActivity extends AppCompatActivity {
    private TextView tvNamaMovie, tvLokasiBioskop, tvTanggal, tvWaktu, tvJenisTicket, tvJumlah, tvHarga;
    ImageView poster;
    int indexFilm;
    Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ticket);

        //buat poster
        indexFilm = getIntent().getIntExtra("indexFilm", 0);
        poster = findViewById(R.id.ivOrderMovie);
        poster.setImageResource(Movie.listofMovie[indexFilm].getGambar());

        tvNamaMovie = findViewById(R.id.tvNamaMovie);
        tvLokasiBioskop = findViewById(R.id.tvLokasiBioskop);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvWaktu = findViewById(R.id.tvWaktu);
        tvJenisTicket = findViewById(R.id.tvJenisTicket);
        tvJumlah = findViewById(R.id.tvBanyakTicket);
        tvHarga = findViewById(R.id.tvHarga);

        //mengambil data dari intent
        String strTicket = getIntent().getStringExtra("objTicket");
        Gson gson = new Gson();
        ticket = gson.fromJson(strTicket, Ticket.class);

        tvNamaMovie.setText(ticket.getJudul());
        tvLokasiBioskop.setText(ticket.getTempat());
        tvTanggal.setText(ticket.getTanggal());
        tvWaktu.setText(ticket.getWaktu());
        tvJenisTicket.setText(ticket.getJenis());
        tvJumlah.setText(ticket.getTotal());
        // harga setnya belum

    }
}
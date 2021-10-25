package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.model.Movie;
import com.example.tubes_uts_e_2.model.Ticket;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

public class PayTicketActivity extends AppCompatActivity {
    private TextView tvNamaMovie, tvLokasiBioskop, tvTanggal, tvWaktu, tvJenisTicket, tvJumlah, tvHarga;
    ImageView poster;
    Button btnCancle;
    int indexFilm;
    Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("payment_notification");
        setContentView(R.layout.activity_pay_ticket);
        getSupportActionBar().hide();

        //buat poster
        indexFilm = getIntent().getIntExtra("indexFilm", 0);
        poster = findViewById(R.id.ivOrderMovie);

        tvNamaMovie = findViewById(R.id.tvNamaMovie);
        tvLokasiBioskop = findViewById(R.id.tvLokasiBioskop);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvWaktu = findViewById(R.id.tvWaktu);
        tvJenisTicket = findViewById(R.id.tvJenisTicket);
        tvJumlah = findViewById(R.id.tvBanyakTicket);
        tvHarga = findViewById(R.id.tvHarga);
        btnCancle = findViewById(R.id.btnCancel);

        //mengambil data dari intent
        String strTicket = getIntent().getStringExtra("objTicket");
        Gson gson = new Gson();
        ticket = gson.fromJson(strTicket, Ticket.class);

        if(ticket.getJudul().equals("Spiderman")) {
            poster.setImageResource(Movie.listofMovie[0].getGambar());
        } else {
            poster.setImageResource(Movie.listofMovie[1].getGambar());
        }

        tvNamaMovie.setText(ticket.getJudul());
        tvLokasiBioskop.setText(ticket.getTempat());
        tvTanggal.setText(ticket.getTanggal());
        tvWaktu.setText(ticket.getWaktu());
        tvJenisTicket.setText(ticket.getJenis());
        tvJumlah.setText(String.valueOf(ticket.getTotal()));

        if(ticket.getJenis().equals("2D")){
            tvHarga.setText("Rp.40.000");
        }
        else{
            tvHarga.setText("Rp.55.000");
        }

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(PayTicketActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }
        });

    }
}
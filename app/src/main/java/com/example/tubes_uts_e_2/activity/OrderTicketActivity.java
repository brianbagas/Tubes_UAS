package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.model.Movie;
import com.example.tubes_uts_e_2.model.Ticket;

public class OrderTicketActivity extends AppCompatActivity {

    int indexFilm;
    ImageView trailer, btnBack;
    TextView tvJudulMovie;

    CardView btnJadwal1, btnJadwal2, btnJadwal3, btnJadwal4, btnJadwal5;
    Button btn2d1, btn2d2, btn2d3, btn2d4;
    Button btn3d1, btn3d2, btn3d3, btn3d4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);
        getSupportActionBar().hide();

        indexFilm = getIntent().getIntExtra("indexFilm", 0);
        tvJudulMovie = findViewById(R.id.tvJudulMovie);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(OrderTicketActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }
        });

        trailer.setImageResource(Movie.listofMovie[indexFilm].getGambar());
        tvJudulMovie.setText(Movie.listofMovie[indexFilm].getJudul());

        btnJadwal1 = findViewById(R.id.btnJadwal1);
        btnJadwal2 = findViewById(R.id.btnJadwal2);
        btnJadwal3 = findViewById(R.id.btnJadwal3);
        btnJadwal4 = findViewById(R.id.btnJadwal4);
        btnJadwal5 = findViewById(R.id.btnJadwal5);

        btn2d1 = findViewById(R.id.btn2Dtime1);
        btn2d2 = findViewById(R.id.btn2Dtime2);
        btn2d3 = findViewById(R.id.btn2Dtime3);
        btn2d4 = findViewById(R.id.btn2Dtime4);

        btn3d1 = findViewById(R.id.btn3Dtime1);
        btn3d2 = findViewById(R.id.btn3Dtime2);
        btn3d3 = findViewById(R.id.btn3Dtime3);
        btn3d4 = findViewById(R.id.btn3Dtime4);
        trailer = findViewById(R.id.trailer);

        Ticket ticketTemp = new Ticket();
        btnJadwal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = setJadwal1("Senin, 01 November 2021");
            }
        });

        btnJadwal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = setJadwal2("Selasa, 02 November 2021");
            }
        });

        btnJadwal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = setJadwal3("Rabu, 03 November 2021");
            }
        });

        btnJadwal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = setJadwal4("Kamis, 04 November 2021");
            }
        });

        btnJadwal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = setJadwal5("Jumat, 05 November 2021");
            }
        });

        btn2d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set2dTime1("12.30");
            }
        });

        btn2d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set2dTime2("13.20");
            }
        });

        btn2d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set2dTime3("16.10");
            }
        });

        btn2d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set2dTime4("19.15");
            }
        });

        btn3d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set3dTime1("12.30");
            }
        });

        btn3d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set3dTime2("13.20");
            }
        });

        btn3d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set3dTime3("16.10");
            }
        });

        btn3d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp = set3dTime4("19.15");
            }
        });
    }
}
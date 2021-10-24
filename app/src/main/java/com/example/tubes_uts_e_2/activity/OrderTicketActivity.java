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
        trailer = findViewById(R.id.trailer);
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

    }
}
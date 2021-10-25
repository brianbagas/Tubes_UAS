package com.example.tubes_uts_e_2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.db.DatabaseTicket;
import com.example.tubes_uts_e_2.db.DatabaseUser;
import com.example.tubes_uts_e_2.model.Movie;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;

public class OrderTicketActivity extends AppCompatActivity {

    int indexFilm;
    ImageView trailer, btnBack;
    TextView tvJudulMovie;
    Ticket ticketTemp = new Ticket();

    CardView btnJadwal1, btnJadwal2, btnJadwal3, btnJadwal4, btnJadwal5;
    Button btn2d1, btn2d2, btn2d3, btn2d4;
    Button btn3d1, btn3d2, btn3d3, btn3d4;
    Button btnOrderNow;

    UserPreferences userPreferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);
        getSupportActionBar().hide();

        userPreferences = new UserPreferences(this);
        user = userPreferences.getUserLogin();

        indexFilm = getIntent().getIntExtra("indexFilm", 0);
        tvJudulMovie = findViewById(R.id.tvJudulMovie);
        trailer = findViewById(R.id.trailer);
        btnBack = findViewById(R.id.btnBack);
        btnOrderNow = findViewById(R.id.btnOrderNow);

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

        findViewBtn();
        setOnclickBtn();
        ticketTemp.setJudul(Movie.listofMovie[indexFilm].getJudul());
        ticketTemp.setUser(user.getUsername());
        ticketTemp.setTempat("Yogyakarta");
        ticketTemp.setTanggal("Senin, 01 November 2021");
        ticketTemp.setWaktu("12.30");
        ticketTemp.setJenis("2D");
        ticketTemp.setTotal(1);

        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTicket(ticketTemp);
                Toast.makeText(OrderTicketActivity.this, "Berhasil memesan tiket!", Toast.LENGTH_SHORT).show();
                Intent homeActivity = new Intent(OrderTicketActivity.this, HomeActivity.class);
                homeActivity.putExtra("order", true);
                startActivity(homeActivity);
                finish();
            }
        });
    }

    public void findViewBtn() {
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
    }

    public void setOnclickBtn() {

        btnJadwal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setTanggal("Senin, 01 November 2021");
            }
        });

        btnJadwal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setTanggal("Selasa, 02 November 2021");
            }
        });

        btnJadwal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setTanggal("Rabu, 03 November 2021");
            }
        });

        btnJadwal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setTanggal("Kamis, 04 November 2021");
            }
        });

        btnJadwal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setTanggal("Jumat, 05 November 2021");
            }
        });

        btn2d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("12.30");
                ticketTemp.setJenis("2D");
            }
        });

        btn2d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("13.20");
                ticketTemp.setJenis("2D");
            }
        });

        btn2d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("16.10");
                ticketTemp.setJenis("2D");
            }
        });

        btn2d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("19.15");
                ticketTemp.setJenis("2D");
            }
        });

        btn3d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("12.30");
                ticketTemp.setJenis("3D");
            }
        });

        btn3d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("13.20");
                ticketTemp.setJenis("3D");
            }
        });

        btn3d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("16.10");
                ticketTemp.setJenis("3D");
            }
        });

        btn3d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketTemp.setWaktu("19.15");
                ticketTemp.setJenis("3D");
            }
        });

    }

    private void addTicket(Ticket ticket) {
        class AddTicket extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseTicket.getInstance(OrderTicketActivity.this)
                        .getDatabase()
                        .ticketDao()
                        .insertTicket(ticket);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(OrderTicketActivity.this, "Berhasil memesan tiket", Toast.LENGTH_SHORT).show();;
            }
        }
        AddTicket add = new AddTicket();
        add.execute();
    }
}
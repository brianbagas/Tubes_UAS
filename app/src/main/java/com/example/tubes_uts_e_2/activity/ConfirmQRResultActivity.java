package com.example.tubes_uts_e_2.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.api.ApiClient;
import com.example.tubes_uts_e_2.api.ApiInterface;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.TicketResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmQRResultActivity extends AppCompatActivity {

    TextView txtQRUsername, txtQRJudul, txtQRTempat, txtQRTanggal, txtQRWaktu, txtQRJenis, txtQRTotal;
    Button btnScanQRActivity, btnSubmitQRActivity;

    Ticket ticket;
    ApiInterface apiService;

    private final ActivityResultLauncher<Intent> cameraResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    Intent intent = result.getData();
                                    String strQRRes =
                                            intent.getStringExtra("QR_RESULT");
                                    setQRResult(strQRRes);
                                } catch (Exception e) {
                                    Toast.makeText(ConfirmQRResultActivity.this, "QR CODE TIDAK VALID!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_qrresult);
        getSupportActionBar().hide();

        txtQRUsername = findViewById(R.id.txtQRUsername);
        txtQRJudul = findViewById(R.id.txtQRJudul);
        txtQRTempat = findViewById(R.id.txtQRTempat);
        txtQRTanggal = findViewById(R.id.txtQRTanggal);
        txtQRWaktu = findViewById(R.id.txtQRWaktu);
        txtQRJenis = findViewById(R.id.txtQRJenis);
        txtQRTotal = findViewById(R.id.txtQRTotal);
        btnScanQRActivity = findViewById(R.id.btnScanQRActivity);
        btnSubmitQRActivity = findViewById(R.id.btnSubmitQRActivity);

        btnScanQRActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraResult.launch(new Intent(ConfirmQRResultActivity.this, QRScannerActivity.class));
            }
        });

        btnSubmitQRActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ticket!=null) {
                    addTicket(ticket);
                    Intent qrActivity = new Intent(ConfirmQRResultActivity.this, HomeActivity.class);
                    startActivity(qrActivity);
                    finish();
                } else {
                    Toast.makeText(ConfirmQRResultActivity.this, "Silahkan scan QR terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setQRResult(String result) {
        Gson gson = new Gson();
        ticket = gson.fromJson(result, Ticket.class);

        txtQRUsername.setText(ticket.getUser());
        txtQRJudul.setText(ticket.getJudul());
        txtQRTempat.setText(ticket.getTempat());
        txtQRTanggal.setText(ticket.getTanggal());
        txtQRWaktu.setText(ticket.getWaktu());
        txtQRJenis.setText(ticket.getJenis());
        txtQRTotal.setText(String.valueOf(ticket.getTotal()));
    }

    private void addTicket(Ticket ticket) {
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<TicketResponse> call = apiService.createTicket(ticket);
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ConfirmQRResultActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ConfirmQRResultActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ConfirmQRResultActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Toast.makeText(ConfirmQRResultActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
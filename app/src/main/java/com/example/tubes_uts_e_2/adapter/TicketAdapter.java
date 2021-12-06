package com.example.tubes_uts_e_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.activity.PayTicketActivity;
import com.example.tubes_uts_e_2.api.ApiClient;
import com.example.tubes_uts_e_2.api.ApiInterface;
import com.example.tubes_uts_e_2.callback.CallBackInterface;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.TicketResponse;
import com.example.tubes_uts_e_2.preferences.UserPreferences;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{

    private List<Ticket> ticketList,filtered;
    private Context context;
    private ImageButton btnDelete;
    public CallBackInterface callbackInterface;

    private ApiInterface apiService;

    private UserPreferences userPreferences;

    public TicketAdapter(CallBackInterface callbackInterface){
        this.callbackInterface = callbackInterface;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserTicket, tvJudul, tvTempat, tvTanggal, tvWaktu, tvJenisTiket, tvTotal;
        RelativeLayout rv_ticket_item;
        ImageButton btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            apiService = ApiClient.getClient().create(ApiInterface.class);

            tvJudul = itemView.findViewById(R.id.inputJudul);
            tvTempat = itemView.findViewById(R.id.tempat);
            tvTanggal = itemView.findViewById(R.id.Tanggal);
            tvWaktu = itemView.findViewById(R.id.waktu);
            tvJenisTiket = itemView.findViewById(R.id.jenisTiket);
            tvTotal = itemView.findViewById(R.id.totalTicket);
            rv_ticket_item = itemView.findViewById(R.id.rv_ticket_item);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public TicketAdapter(List<Ticket> ticketList, Context context) {
        this.ticketList = ticketList;
        filtered = new ArrayList<>(ticketList);
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //init view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = filtered.get(position);
        holder.tvJudul.setText(ticket.getJudul());
        holder.tvTempat.setText(ticket.getTempat());
        holder.tvTanggal.setText(ticket.getTanggal());
        holder.tvWaktu.setText(ticket.getWaktu());
        holder.tvJenisTiket.setText(ticket.getJenis());
        holder.tvTotal.setText(String.valueOf(ticket.getTotal()));

        holder.rv_ticket_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PayTicketActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Gson gson = new Gson();
                String strTicket = gson.toJson(ticket);
                intent.putExtra("objTicket", strTicket);
                context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Ticket ticket = ticketList.get(position);
//
//                ticketList.remove(position);
//                notifyItemRemoved(position);

                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data produk ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context.getApplicationContext(), "HEHEHEHEH", Toast.LENGTH_SHORT).show();
                                deleteTicket(ticket.getId());

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public void deleteTicket(int id) {
        // TODO: Tambahkan fungsi untuk menghapus data buku.
        Call<TicketResponse> call = apiService.deleteTicket(id);
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(context.getApplicationContext(), "loh he", Toast.LENGTH_SHORT).show();
                    callbackInterface.callBackMethod();
                }else {
                    try { JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(context.getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), "apa sih", Toast.LENGTH_SHORT).show();
                    }
                }



            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}


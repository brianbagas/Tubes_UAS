package com.example.tubes_uts_e_2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{

    private List<Ticket> ticketList;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserTicket, tvJudul, tvTempat, tvTanggal, tvWaktu, tvJenisTiket, tvTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserTicket = itemView.findViewById(R.id.tvUserTicket);
            tvJudul = itemView.findViewById(R.id.inputJudul);
            tvTempat = itemView.findViewById(R.id.tempat);
            tvTanggal = itemView.findViewById(R.id.Tanggal);
            tvWaktu = itemView.findViewById(R.id.waktu);
            tvJenisTiket = itemView.findViewById(R.id.jenisTiket);
            tvTotal = itemView.findViewById(R.id.total);
        }
    }

    public TicketAdapter(List<Ticket> ticketList, Context context) {
        this.ticketList = ticketList;
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
        Ticket ticket = ticketList.get(position);
        holder.tvUserTicket.setText(UserPreferences.KEY_USERNAME);
        holder.tvJudul.setText(ticket.getJudul());
        holder.tvTempat.setText(ticket.getTempat());
        holder.tvTanggal.setText(ticket.getTanggal());
        holder.tvWaktu.setText(ticket.getWaktu());
        holder.tvJenisTiket.setText(ticket.getJenis());
        holder.tvTotal.setText(ticket.getTotal());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
}


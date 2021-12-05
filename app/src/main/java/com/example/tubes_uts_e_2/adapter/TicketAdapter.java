package com.example.tubes_uts_e_2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.activity.OrderTicketActivity;
import com.example.tubes_uts_e_2.activity.PayTicketActivity;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{

    private List<Ticket> ticketList;
    private Context context;

    private UserPreferences userPreferences;
    private User user;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserTicket, tvJudul, tvTempat, tvTanggal, tvWaktu, tvJenisTiket, tvTotal;
        RelativeLayout rv_ticket_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.inputJudul);
            tvTempat = itemView.findViewById(R.id.tempat);
            tvTanggal = itemView.findViewById(R.id.Tanggal);
            tvWaktu = itemView.findViewById(R.id.waktu);
            tvJenisTiket = itemView.findViewById(R.id.jenisTiket);
            tvTotal = itemView.findViewById(R.id.totalTicket);
            rv_ticket_item = itemView.findViewById(R.id.rv_ticket_item);
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
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public void setTicketList(List<Ticket> ticketList){
        this.ticketList = ticketList;
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvJudul, tvTempat, tvTanggal, tvWaktu, tvJenisTiket, tvTotal;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvJudul = itemView.findViewById(R.id.tvJudul);
//            tvTempat = itemView.findViewById(R.id.tv_nama);
//            tvTanggal = itemView.findViewById(R.id.tv_stok);
//            tvWaktu = itemView.findViewById(R.id.iv_gambar);
//            tvJenisTiket = itemView.findViewById(R.id.btn_delete);
//            tvTotal = itemView.findViewById(R.id.cv_produk);
//        }
//    }
}


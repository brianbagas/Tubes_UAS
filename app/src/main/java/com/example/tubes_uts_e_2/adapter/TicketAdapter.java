package com.example.tubes_uts_e_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.model.Ticket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.viewHolder>{

    private final Ticket[] listTiket;

    public TicketAdapter(Ticket[] listTiket) {
        this.listTiket = listTiket;
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        TextView judulTicket;
        TextView tempat;
        TextView tglnonton;
        TextView waktu;
        TextView jenis;
        TextView total;

        public viewHolder(@NonNull View itemView){
            super(itemView);
            judulTicket = itemView.findViewById(R.id.inputJudul);
            tempat = itemView.findViewById(R.id.tempat);
            tglnonton = itemView.findViewById(R.id.Tanggal);
            waktu = itemView.findViewById(R.id.waktu);
            jenis = itemView.findViewById(R.id.jenisTiket);
            total = itemView.findViewById(R.id.total);
        }
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_ticket,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.judulTicket.setText(listTiket[position].getJudul());
        holder.tempat.setText(listTiket[position].getTempat());
        holder.tglnonton.setText(listTiket[position].getTglnonton());
        holder.waktu.setText(listTiket[position].getWaktu()+"");
        holder.jenis.setText(listTiket[position].getJenis());
        holder.total.setText(listTiket[position].getTotal()+"");
    }


    @Override
    public int getItemCount() {
        return listTiket.length;
    }


}


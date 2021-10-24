package com.example.tubes_uts_e_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.model.Movie;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder>{
    private final Movie[] listMovie;

    public HomeAdapter(Movie[] listMovie) {
        this.listMovie = listMovie;
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView judul;

        public viewHolder(@NonNull View itemView){
            super(itemView);
            gambar = itemView.findViewById(R.id.fotomovie);
            judul = itemView.findViewById(R.id.judulfilm);
        }
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.gambar.setImageResource(listMovie[position].getGambar());
        holder.judul.setText(listMovie[position].getJudul());


    }

    @Override
    public int getItemCount() {
        return listMovie.length;
    }


}

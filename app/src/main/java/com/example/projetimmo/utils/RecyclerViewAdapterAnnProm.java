package com.example.projetimmo.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.AnnonceActivity;
import com.example.projetimmo.AnnoncePromoteurActivity;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewAdapterAnnProm extends RecyclerView.Adapter<RecyclerViewAdapterAnnProm.MyViewHolder> {

    private Context mcontext ;
    private List<itemToSell> mData ;

    public RecyclerViewAdapterAnnProm(Context mcontext, List<itemToSell> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mcontext);
        view = mInflater.inflate(R.layout.cardview_item_ann_prom,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nom.setText(mData.get(position).getTitle());
        holder.adresse.setText(mData.get(position).getLocation().getAddress());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        holder.date.setText(df.format(mData.get(position).getDateDeLivraison()));
        holder.prix.setText(String.valueOf(mData.get(position).getPrice()));
        Picasso.get().load(mData.get(position).getUrlPicture()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, AnnoncePromoteurActivity.class);
                intent.putExtra("nom", mData.get(position).getTitle());
                intent.putExtra("localisation", mData.get(position).getLocation().getAddress());
                intent.putExtra("date", mData.get(position).getDateDeLivraison().toString());
                intent.putExtra("prix", mData.get(position).getPrice());
                intent.putExtra("thumbnail", mData.get(position).getUrlPicture());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nom , adresse , date , prix ;
        ImageView image ;
        CardView cardView ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nomannonce);
            adresse = itemView.findViewById(R.id.localisation);
            date = itemView.findViewById(R.id.date);
            prix = itemView.findViewById(R.id.prix);
            image = itemView.findViewById(R.id.imagebien);
            cardView = itemView.findViewById(R.id.cardviewid);
        }
    }


}


package com.example.projetimmo.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.AnnonceActivity;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.R;
import com.example.projetimmo.data.RetrofitClient;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapterFavoris extends RecyclerView.Adapter<RecyclerViewAdapterFavoris.MyViewHolder> {
      TinyDB tinyDB;
      private Context mcontext;
      private List<itemToSell> mData;
      private static final String TAG = "RecyclerViewAdapterFavo";
      String token;

      public RecyclerViewAdapterFavoris(Context mcontext, List<itemToSell> mData, String token) {
            this.mcontext = mcontext;
            this.mData = mData;
            this.tinyDB = new TinyDB(mcontext);
            this.token = token;
      }

      @NonNull
      @Override
      public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater mInflater = LayoutInflater.from(mcontext);
            view = mInflater.inflate(R.layout.cardview_item_favs, parent, false);
            return new MyViewHolder(view);
      }

      @Override
      public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.nom.setText(mData.get(position).getTitle());
            holder.adresse.setText(mData.get(position).getLocation().getAddress());
            final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            holder.date.setText(df.format(mData.get(position).getDateDeLivraison()));
            holder.prix.setText(String.valueOf(mData.get(position).getPrice()));
            Picasso.get().load(mData.get(position).getUrlPicture()).into(holder.image);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Log.e(TAG, "onClick: 3");
                        RetrofitClient.build().getUserByID(mData.get(position).getUserId(), "Bearer " + token)
                                .enqueue(new Callback<User>() {
                                      @Override
                                      public void onResponse(Call<User> call, Response<User> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onClick: 4");
                                                  tinyDB.putObject("user_him", response.body());
                                                  tinyDB.putObject("selectedItem", mData.get(position));
                                                  Log.e(TAG, "onResponse: userHim: " + response.body());
                                                  Log.e(TAG, "onResponse: userHim: " + mData.get(position));
                                                  mcontext.startActivity(new Intent(mcontext, AnnonceActivity.class));
                                            } else {
                                                  Log.e(TAG, "onResponse code: " + response.code());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<User> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getMessage());
                                      }
                                });
                  }
            });

      }

      @Override
      public int getItemCount() {
            return mData.size();
      }

      public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView nom, adresse, date, prix;
            ImageView image;
            CardView cardView;

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


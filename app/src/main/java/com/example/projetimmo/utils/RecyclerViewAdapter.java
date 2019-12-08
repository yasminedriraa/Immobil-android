package com.example.projetimmo.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.AnnonceActivity;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.R;
import com.example.projetimmo.data.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
      private static final String TAG = "RecyclerViewAdapter";
      private Context mcontext;
      private List<itemToSell> mData;
      TinyDB tinydb;
      String token;
      List<Integer> ints;
      boolean b;

      public RecyclerViewAdapter(Context mcontext, List<itemToSell> mData, String token, boolean b) {
            this.mcontext = mcontext;
            this.mData = mData;
            this.token = token;
            this.b = b;
      }

      @NonNull
      @Override
      public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater mInflater = LayoutInflater.from(mcontext);
            view = mInflater.inflate(R.layout.cardview_item, parent, false);
            return new MyViewHolder(view);
      }


      @Override
      public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            tinydb = new TinyDB(mcontext);
            ints = tinydb.getListInt("favoritList");
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            holder.nom.setText(mData.get(position).getTitle());
            holder.housesNumber.setText("" + mData.get(position).getAvailableHouses());
            holder.adresse.setText(mData.get(position).getLocation().getAddress());
            final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            holder.date.setText(mData.get(position).getDateDeLivraison());
            holder.prix.setText(String.valueOf(mData.get(position).getPrice()));
            if (mData.get(position).getUrlPicture().length() != 0)
                  Picasso.get().load(mData.get(position).getUrlPicture()).into(holder.image);
            if (ints.contains(mData.get(position).getId())) {
                  holder.like.setImageResource(R.drawable.like_red);
                  holder.like.setTag("like_red");
            } else {
                  holder.like.setImageResource(R.drawable.like);
                  holder.like.setTag("like");
            }
            holder.like.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (holder.like.getTag().equals("like")) {
                              holder.like.setImageResource(R.drawable.like_red);
                              holder.like.setTag("like_red");
                              addToFavorit(mData.get(position).getId(), userResponse.getUser().getId(), token, holder);
                        } else {
                              holder.like.setImageResource(R.drawable.like);
                              holder.like.setTag("like");
                              removeFromFavorit(mData.get(position).getId(), userResponse.getUser().getId(), token, holder);
                        }
                  }
            });
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Log.e(TAG, "onClick: 1");
                        Log.e(TAG, "onClick: " + mData.get(position).getUserId());
                        RetrofitClient.build().getUserByID(mData.get(position).getUserId(), "Bearer " + token)
                                .enqueue(new Callback<User>() {
                                      @Override
                                      public void onResponse(Call<User> call, Response<User> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onClick: 2");
                                                  tinydb.putObject("user_him", response.body());
                                                  tinydb.putObject("selectedItem", mData.get(position));
                                                  Intent i = new Intent(mcontext, AnnonceActivity.class);
                                                  i.putExtra("myAnnonces", b);
                                                  mcontext.startActivity(i);

                                            } else {
                                                  Log.e(TAG, "onResponse code: " + response.code());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<User> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getMessage());
                                            Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
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
            ImageView image, like;
            CardView cardView;
            Button housesNumber;

            public MyViewHolder(@NonNull View itemView) {
                  super(itemView);
                  nom = itemView.findViewById(R.id.nomannonce);
                  adresse = itemView.findViewById(R.id.localisation);
                  date = itemView.findViewById(R.id.date);
                  prix = itemView.findViewById(R.id.prix);
                  image = itemView.findViewById(R.id.imagebien);
                  like = itemView.findViewById(R.id.like);
                  cardView = itemView.findViewById(R.id.cardviewid);
                  housesNumber = itemView.findViewById(R.id.number_houses);
            }
      }

      public void addToFavorit(final int id, int id_user, String token, final MyViewHolder holder) {
            RetrofitClient.build().postFavoritAnnonce(id, id_user, "Bearer " + token)
                    .enqueue(new Callback<ResponseBody>() {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                      holder.like.setImageResource(R.drawable.like_red);
                                      holder.like.setTag("like_red");
                                      Log.e(TAG, "onResponse code: " + response.code());
                                      ints = tinydb.getListInt("favoritList");
                                      ints.add(id);
                                      tinydb.putListInt("favoritList", ints);

                                } else {
                                      Log.e(TAG, "onResponse code: " + response.code());
                                }
                          }

                          @Override
                          public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.getMessage());
                          }
                    });
      }

      public void removeFromFavorit(final int id, int id_user, String token, final MyViewHolder holder) {
            RetrofitClient.build().delFavoritAnnonce(id, id_user, "Bearer " + token)
                    .enqueue(new Callback<ResponseBody>() {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                      holder.like.setImageResource(R.drawable.like);
                                      holder.like.setTag("like");
                                      ints = tinydb.getListInt("favoritList");
                                      ints.remove(Integer.valueOf(id));
                                      tinydb.putListInt("favoritList", ints);

                                } else {
                                      Log.e(TAG, "onResponse code: " + response.code());
                                }
                          }

                          @Override
                          public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + call.toString());
                          }
                    });
      }
}


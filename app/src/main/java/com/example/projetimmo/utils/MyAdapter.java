package com.example.projetimmo.utils;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.DetailNoteActivity;
import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.R;
import com.example.projetimmo.data.RetrofitClient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
      private static final String TAG = "RecyclerViewAdapter";
      Context mcontext;
      List<Notes> mData;
      String token;

      public MyAdapter(Context mcontext, List<Notes> mData, String token) {
            this.mcontext = mcontext;
            this.mData = mData;
            this.token = token;
      }

      @NonNull
      @Override
      public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater mInflater = LayoutInflater.from(mcontext);
            view = mInflater.inflate(R.layout.note_adapter, parent, false);
            return new MyViewHolder(view);
      }


      @Override
      public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            holder.title.setText(mData.get(position).getTitle());
            holder.noteText.setText(mData.get(position).getContent());
            holder.whole.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent i = new Intent(mcontext, DetailNoteActivity.class);
                        i.putExtra("id", mData.get(position).getId());
                        i.putExtra("title", mData.get(position).getTitle());
                        i.putExtra("content", mData.get(position).getContent());
                        i.putExtra("user_id", mData.get(position).getUserId());
                        mcontext.startActivity(i);
                  }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        RetrofitClient.build().deleteNotes(mData.get(position).getId(), "Bearer " + token)
                                .enqueue(new Callback<ResponseBody>() {
                                      @Override
                                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()) {
                                                  Toast.makeText(mcontext, "Note deleted successfully ", Toast.LENGTH_LONG).show();
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
            });

      }

      @Override
      public int getItemCount() {
            return mData.size();
      }

      public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView title, noteText;
            ImageView delete;
            CardView whole;

            public MyViewHolder(@NonNull View itemView) {
                  super(itemView);
                  title = itemView.findViewById(R.id.nombien);
                  noteText = itemView.findViewById(R.id.manote);
                  delete = itemView.findViewById(R.id.delete_note);
                  whole = itemView.findViewById(R.id.whole);
            }
      }


}







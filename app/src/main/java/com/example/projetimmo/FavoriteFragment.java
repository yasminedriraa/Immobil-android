package com.example.projetimmo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetimmo.Models.Categorie;
import com.example.projetimmo.Models.FavoritRetrievedData;
import com.example.projetimmo.Models.FavoriteAnnonceResponse;
import com.example.projetimmo.Models.Location;
import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.RecyclerViewAdapter;
import com.example.projetimmo.utils.RecyclerViewAdapterFavoris;
import com.example.projetimmo.utils.TinyDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

      List<itemToSell> annonces;
      private static final String TAG = "FavoriteFragment";
      RecyclerView myrv;

      public FavoriteFragment() {
      }


      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_favorite, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            TinyDB tinydb = new TinyDB(getContext());
            annonces = new ArrayList<>();
            myrv = view.findViewById(R.id.recyclerviewfav);
            try {
                  final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
                  RetrofitClient.build().getFavoritAnnonceByIdUser(userResponse.getUser().getId(), "Bearer " + userResponse.getToken())
                          .enqueue(new Callback<FavoritRetrievedData>() {
                                @Override
                                public void onResponse(Call<FavoritRetrievedData> call, Response<FavoritRetrievedData> response) {
                                      if (response.isSuccessful()) {
                                            Log.e(TAG, "onResponse: All good");
                                            annonces = new ArrayList<>();
                                            annonces = response.body().getItemToSells();
                                            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), annonces, userResponse.getToken(), false);
                                            myrv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                                            myrv.setAdapter(myAdapter);
                                      } else {
                                            Log.e(TAG, "onResponse: I found an error code: " + response.code());
                                      }
                                }

                                @Override
                                public void onFailure(Call<FavoritRetrievedData> call, Throwable t) {
                                      Log.e(TAG, "onFailure: " + t.getMessage());
                                      Log.e(TAG, "onFailure: " + call.toString());
                                }
                          });
            } catch (Exception e) {
                  Log.e(TAG, "onViewCreated: " + e.getMessage());
            }

      }

      @Override
      public void onAttach(Context context) {
            super.onAttach(context);

      }

      @Override
      public void onDetach() {
            super.onDetach();
      }

}

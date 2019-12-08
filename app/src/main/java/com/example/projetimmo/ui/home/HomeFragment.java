package com.example.projetimmo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.FilterFragment;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.R;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.RecyclerViewAdapter;
import com.example.projetimmo.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
      private static final String TAG = "HomeFragment";
      ImageView editt;
      CardView gerernotes;
      CardView disconnect;
      TextView lastName, firstName, email, phoneNumber;
      List<itemToSell> annonces;
      RecyclerView myrv;
      TextView search;

      public HomeFragment() {
            // Required empty public constructor
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            annonces = new ArrayList<>();
            TinyDB tinydb = new TinyDB(getContext());
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            search = view.findViewById(R.id.text_view);
            myrv = view.findViewById(R.id.recyclerviewid);
            search.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        FilterFragment nextFrag = new FilterFragment();
                        if (userResponse.getClaims().getScopes().equals("ROLE_PROMOTEUR")) {
                              getActivity().getSupportFragmentManager().beginTransaction()
                                      .replace(R.id.dashboardFrame, nextFrag)
                                      .addToBackStack(null)
                                      .commit();
                        }
                        else{
                              getActivity().getSupportFragmentManager().beginTransaction()
                                      .replace(R.id.frame_main_activity, nextFrag)
                                      .addToBackStack(null)
                                      .commit();

                        }
                  }
            });
            Fragment fragment = getFragmentManager().findFragmentById(R.id.frame_main_activity);
            Fragment fragment2 = getFragmentManager().findFragmentById(R.id.dashboardFrame);
            String tag = null;
            String tag2 = null;
            try {
                  tag = fragment.getTag();
            } catch (Exception e) {
            }
            try {
                  tag2 = fragment2.getTag();
            } catch (Exception e) {
            }
            if (tag == null)
                  tag = "";
            if (tag2 == null)
                  tag2 = "";
            if (tag2.equals("fromFilter")) {
                  List<itemToSell> itemToSells = tinydb.getItemToSell("filtredObjects");
                  RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), itemToSells, userResponse.getToken(), false);
                  myrv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                  myrv.setAdapter(myAdapter);
            } else {
                  Log.e(TAG, "onViewCreated: " + tag2);
                  Log.e(TAG, "onViewCreated: " + tag);
                  if (tag2.length() != 0) {
                       RetrofitClient.build().getAnnonceByUserID(Integer.parseInt(tag2), "Bearer " + userResponse.getToken())
                                .enqueue(new Callback<List<itemToSell>>() {
                                      @Override
                                      public void onResponse(Call<List<itemToSell>> call, Response<List<itemToSell>> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onResponse: It's working tadaaa");
                                                  annonces = new ArrayList<>();
                                                  annonces = response.body();
                                                  RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), annonces, userResponse.getToken(), true);
                                                  myrv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                                                  myrv.setAdapter(myAdapter);
                                            } else {
                                                  Log.e(TAG, "onResponse: I found an error code: " + response.code());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<List<itemToSell>> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getCause().getMessage());

                                      }
                                });
                  } else {
                        RetrofitClient.build().getAllAnnonce("Bearer " + userResponse.getToken())
                                .enqueue(new Callback<List<itemToSell>>() {
                                      @Override
                                      public void onResponse(Call<List<itemToSell>> call, Response<List<itemToSell>> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onResponse: It's working tadaaa");
                                                  annonces = new ArrayList<>();
                                                  annonces = response.body();
                                                  RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), annonces, userResponse.getToken(), false);
                                                  myrv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                                                  myrv.setAdapter(myAdapter);
                                            } else {
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<List<itemToSell>> call, Throwable t) {

                                      }
                                });
                  }
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

package com.example.projetimmo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.MyAdapter;
import com.example.projetimmo.utils.TinyDB;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
      private static final String TAG = "ProfileFragment";
      ImageView editt;
      CardView gerernotes;
      CardView disconnect;
      TextView lastName, firstName, email, phoneNumber;
      ConstraintLayout constNotes;
      RecyclerView mRecyclerView;
      MyAdapter myAdapter;
      CircleImageView profilePic;

      public ProfileFragment() {
            // Required empty public constructor
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_profile, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            TinyDB tinydb = new TinyDB(getContext());
            UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            editt = view.findViewById(R.id.edit);
            gerernotes = view.findViewById(R.id.notes);
            constNotes = view.findViewById(R.id.const_notes);
            disconnect = view.findViewById(R.id.disconnect);
            profilePic = view.findViewById(R.id.imageprofi);
            lastName = view.findViewById(R.id.last_name);
            firstName = view.findViewById(R.id.first_name);
            email = view.findViewById(R.id.email);

            phoneNumber = view.findViewById(R.id.phone_number);
            mRecyclerView = view.findViewById(R.id.recyclerviewnote);
            lastName.setText(userResponse.getUser().getLastName());
            firstName.setText(userResponse.getUser().getFirstName());
            phoneNumber.setText(userResponse.getUser().getPhoneNumber());
            email.setText(userResponse.getUser().getEmail());


            Glide.with(getActivity())
                    .load(userResponse.getUser()
                            .getUrlProfilePicture())
                    .apply(new RequestOptions()
                            .error(R.drawable.ic_close))
                    .into(profilePic);
            ArrayList<Notes> notes = new ArrayList<>();
            getAllNotes(userResponse.getUser().getId(), userResponse.getToken());


            disconnect.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        DatabaseReference.goOffline();
                        Intent i = new Intent(getContext(), HomeActivity.class);
                        startActivity(i);
                  }
            });
            editt.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent i = new Intent(getContext(), EditProfilActivity.class);
                        startActivity(i);
                  }
            });
            gerernotes.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (constNotes.getVisibility() == View.VISIBLE)
                              constNotes.setVisibility(View.GONE);
                        else
                              constNotes.setVisibility(View.VISIBLE);
                  }
            });
      }


      @Override
      public void onAttach(Context context) {
            super.onAttach(context);
      }

      @Override
      public void onDetach() {
            super.onDetach();
      }

      public void getAllNotes(int id_user, final String token) {
            RetrofitClient.build().getNotesByUserId(id_user, "Bearer " + token)
                    .enqueue(new Callback<List<Notes>>() {
                          @Override
                          public void onResponse(Call<List<Notes>> call, Response<List<Notes>> response) {
                                if (response.isSuccessful()) {
                                      mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      myAdapter = new MyAdapter(getContext(), response.body(), token);
                                      mRecyclerView.setAdapter(myAdapter);

                                } else {
                                      Log.e(TAG, "onResponse code: " + response.code());
                                }
                          }

                          @Override
                          public void onFailure(Call<List<Notes>> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + call.toString());
                          }
                    });

      }

}

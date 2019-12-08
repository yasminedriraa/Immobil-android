package com.example.projetimmo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInfoCompanyFragment extends Fragment {
      private static final String TAG = "EditInfoCompagnieActivi";
      TextInputEditText companyName, addresseCompany, emailCompany, numeroCompany;
      Button save;
      User userModified;
      TinyDB tinydb;
      UserResponse userResponse;


      public EditInfoCompanyFragment() {
      }


      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_edit_info_company, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            tinydb = new TinyDB(getContext());
            userResponse = tinydb.getObject("user", UserResponse.class);
            userModified = userResponse.getUser();

            companyName = view.findViewById(R.id.company_name_et);
            addresseCompany = view.findViewById(R.id.addresse_et);
            emailCompany = view.findViewById(R.id.email_et);
            numeroCompany = view.findViewById(R.id.numero_et);

            companyName.setText(userResponse.getUser().getCompany());
            addresseCompany.setText(userResponse.getUser().getCompanyAddress());
            emailCompany.setText(userResponse.getUser().getCompagnieEmail());
            numeroCompany.setText(userResponse.getUser().getCompagniePhone());

            save = view.findViewById(R.id.button);
            save.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (verifie()) {
                              userModified.setCompany(companyName.getText().toString());
                              userModified.setCompanyAddress(addresseCompany.getText().toString());
                              userModified.setCompagnieEmail(emailCompany.getText().toString());
                              userModified.setCompagniePhone(numeroCompany.getText().toString());
                              RetrofitClient.build().updateProfile(userModified, "Bearer " + userResponse.getToken())
                                      .enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                  if (response.isSuccessful()) {
                                                        UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
                                                        userResponse.setUser(response.body());
                                                        tinydb.putObject("user", userResponse);
                                                        Intent i;
                                                        Toast.makeText(getContext(), "Vos informations sont modifier", Toast.LENGTH_LONG).show();
                                                        if (userResponse.getClaims().getScopes().contains("PROMO"))
                                                              i = new Intent(getContext(), DashbordActivity.class);
                                                        else
                                                              i = new Intent(getContext(), MainActivity.class);
                                                        i.putExtra("fragment", "profile");
                                                        startActivity(i);

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
                  }
            });
      }

      public Boolean verifie() {
            Boolean toReturn = true;
            if (companyName.getText().length() == 0) {
                  companyName.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  companyName.setError(null);
            if (addresseCompany.getText().length() == 0) {
                  addresseCompany.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  addresseCompany.setError(null);
            if (emailCompany.getText().length() == 0) {
                  emailCompany.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  emailCompany.setError(null);
            if (numeroCompany.getText().length() == 0) {
                  numeroCompany.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  numeroCompany.setError(null);
            return toReturn;
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

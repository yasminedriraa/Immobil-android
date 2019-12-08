package com.example.projetimmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInfoCompagnieActivity extends AppCompatActivity {
      private static final String TAG = "EditInfoCompagnieActivi";
      TextInputEditText companyName, addresseCompany, emailCompany, numeroCompany;
      Button save;
      User userModified;
      TinyDB tinydb;
      UserResponse userResponse;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_info_compagnie);
            tinydb = new TinyDB(getApplicationContext());
            userResponse = tinydb.getObject("user", UserResponse.class);
            userModified = userResponse.getUser();
            companyName = findViewById(R.id.company_name_et);
            addresseCompany = findViewById(R.id.addresse_et);
            emailCompany = findViewById(R.id.email_et);
            numeroCompany = findViewById(R.id.numero_et);
            numeroCompany = findViewById(R.id.numero_et);
            save = findViewById(R.id.button);
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
                                                        TinyDB tinyDB = new TinyDB(getApplicationContext());
                                                        //UserResponse user = tinyDB.getObject("user", UserResponse.class);
                                                        Toast.makeText(getApplicationContext(), "Vos informations sont modifier", Toast.LENGTH_LONG).show();
                                                        if (userResponse.getClaims().getScopes().contains("PROMO"))
                                                              i = new Intent(EditInfoCompagnieActivity.this, DashbordActivity.class);
                                                        else
                                                              i = new Intent(EditInfoCompagnieActivity.this, MainActivity.class);
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
}

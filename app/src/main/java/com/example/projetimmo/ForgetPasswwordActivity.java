package com.example.projetimmo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetimmo.data.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswwordActivity extends AppCompatActivity {
      TextInputEditText email;
      Button confirm;
      private static final String TAG = "ForgetPasswwordActivity";
      Toolbar toolbar;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forget_passwword);
            setSupportActionBar(toolbar);

            email = findViewById(R.id.email);
            confirm = findViewById(R.id.recover_password);
            toolbar = findViewById(R.id.toolbar7);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        ForgetPasswwordActivity.super.onBackPressed();
                  }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (isValidEmail(email.getText().toString())) {
                              RetrofitClient.build().recoverPassword(email.getText().toString())
                                      .enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                  if (response.code() == 200) {
                                                        Toast.makeText(getApplicationContext(), "Un e-mail est envoyee a votre boite mail", Toast.LENGTH_LONG).show();
                                                        Log.e(TAG, "onResponse code: " + response.code());
                                                        startActivity(new Intent(ForgetPasswwordActivity.this, LoginActivity.class));


                                                  } else if (response.code() == 404) {
                                                        email.setError("e-mail invalide!!");
                                                        Log.e(TAG, "onResponse code: " + response.code());
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
            });
      }

      public static boolean isValidEmail(CharSequence target) {
            return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
      }
}

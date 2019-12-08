package com.example.projetimmo.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginWebService {
      private static final String TAG = "LoginWebService";

      public void login(final LoginWebServiceListener listener, final LoginRequest request, final Context context) {
            RetrofitClient.build().login(request)
                    .enqueue(new Callback<UserResponse>() {
                          @Override
                          public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                      Log.e(TAG, "onResponse: " + response.body().toString());
                                      TinyDB tinydb = new TinyDB(context);
                                      tinydb.putObject("user", response.body());
                                      if (response.body().getClaims().getScopes().equals("ROLE_PROMOTEUR"))
                                            listener.onSuccess(0);
                                      else
                                            listener.onSuccess(1);
                                } else {
                                      Log.e(TAG, "onResponse: " + response.code());
                                      Log.e(TAG, "onResponse: " + response.message());
                                      Log.e(TAG, "onResponse: " + response.errorBody().toString());
                                      listener.onError(response.message());
                                }
                          }

                          @Override
                          public void onFailure(Call<UserResponse> call, Throwable t) {
                                listener.onFailure(t.getMessage());
                                Log.e(TAG, "onFailure: " + t.getMessage());
                          }
                    });
      }
}

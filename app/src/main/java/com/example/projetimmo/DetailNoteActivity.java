package com.example.projetimmo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNoteActivity extends AppCompatActivity {

      private static final String TAG = "DetailNoteActivity";
      //TextView titleTV;
      EditText note;
      ImageView back, update;
      //int id;
      itemToSell item;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_note);
            //titleTV = findViewById(R.id.title);
            TinyDB tinydb = new TinyDB(getApplicationContext());
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            item = tinydb.getObject("selectedItem", itemToSell.class);
            Log.e(TAG, "onCreate: " + item.toString());
            note = findViewById(R.id.manote);
            back = findViewById(R.id.back);
            update = findViewById(R.id.update);
            Bundle b = getIntent().getExtras();
            if (b != null) {
                  //id = b.getInt("id");
                  String title = b.getString("title");
                  String content = b.getString("content");
                  note.setText(content);
            }
            back.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        DetailNoteActivity.super.onBackPressed();

                  }
            });

            update.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        RetrofitClient.build().putNotes(new Notes(item.getId(), "", note.getText().toString(), userResponse.getUser().getId()), "Bearer " + userResponse.getToken())
                                .enqueue(new Callback<ResponseBody>() {
                                      @Override
                                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            Intent i;
                                            TinyDB tinyDB = new TinyDB(getApplicationContext());
                                            UserResponse user;
                                            user = tinyDB.getObject("user", UserResponse.class);
                                            if (response.isSuccessful()) {
                                                  Toast.makeText(getApplicationContext(), "Vos informations sont modifier", Toast.LENGTH_LONG).show();
                                                  if (user.getClaims().getScopes().equals("ROLE_PROMOTEUR")) {
                                                        i = new Intent(DetailNoteActivity.this, DashbordActivity.class);
                                                  } else {
                                                        i = new Intent(DetailNoteActivity.this, MainActivity.class);
                                                  }
                                                  i.putExtra("fragment", "profile");
                                                  startActivity(i);

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
}

package com.example.projetimmo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.NotesRequest;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.idObject;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.MyAdapter;
import com.example.projetimmo.utils.TinyDB;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreationNoteActivity extends AppCompatActivity {
      ImageView update, back;
      EditText myNote;
      private static final String TAG = "CreationNoteActivity";
      itemToSell item;
      TinyDB tinyDB;
      UserResponse user;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_creation_note);
            tinyDB = new TinyDB(getApplicationContext());
            user = tinyDB.getObject("user", UserResponse.class);
            update = findViewById(R.id.update);
            back = findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        CreationNoteActivity.super.onBackPressed();
                  }
            });
            tinyDB = new TinyDB(this);
            myNote = findViewById(R.id.manote);
            final Bundle b = getIntent().getExtras();
            update.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (b != null) {
                              int id = b.getInt("id_annonce");
                              int id_user = b.getInt("id_user");
                              String token = b.getString("token");
                              item = tinyDB.getObject("selectedItem", itemToSell.class);
                              NotesRequest notes = new NotesRequest(new idObject(item.getId()), myNote.getText().toString(), "", id_user);
                              Log.e(TAG, "onCreate: " + notes.toString());
                              RetrofitClient.build().postNotes(notes, "Bearer " + token)
                                      .enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                  if (response.isSuccessful()) {
                                                        if (user.getClaims().getScopes().equals("ROLE_PROMOTEUR"))
                                                              startActivity(new Intent(CreationNoteActivity.this, DashbordActivity.class));
                                                        else
                                                              startActivity(new Intent(CreationNoteActivity.this, MainActivity.class));

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
                  }
            });
      }
}

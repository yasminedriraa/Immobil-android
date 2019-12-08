package com.example.projetimmo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.Apdaters.MessageAdapterr;
import com.example.projetimmo.Models.Chat;
import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.utils.TinyDB;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.projetimmo.utils.Utils.readMessage;
import static com.example.projetimmo.utils.Utils.sendMessage;

public class ChatRoomActivity extends AppCompatActivity {
      private static final String TAG = "ChatRoomActivity";
      // DatabaseReference reference;
      Intent intent;
      ImageView btn_send;
      EditText text_send;
      RecyclerView recyclerView;
      itemToSell itemToSell;
      MessagesResponse messagesResponse;
      static MessageAdapterr messageAdapter;
      Toolbar toolbar;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat_room);
            toolbar = findViewById(R.id.toolbarMessage);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        ChatRoomActivity.super.onBackPressed();
                  }
            });
            TinyDB tinyDB = new TinyDB(getApplicationContext());
            messagesResponse = tinyDB.getObject("the_conversation", MessagesResponse.class);
            final UserResponse userMe = tinyDB.getObject("user", UserResponse.class);
            recyclerView = findViewById(R.id.recycleViewMessage);

//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
//            recyclerView.setLayoutManager(linearLayoutManager);
//            messageAdapter = new MessageAdapterr(getApplicationContext(), messagesResponse.discussion, userMe.getUser(), messagesResponse.getUser_him());
//            recyclerView.setAdapter(messageAdapter);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final ProgressDialog progressdialog = new ProgressDialog(ChatRoomActivity.this);
            progressdialog.setMessage("Please Wait....");
            progressdialog.show();
            int idHim = messagesResponse.getUser_me().getId();
            int idMe = messagesResponse.getUser_him().getId();

            Log.e(TAG, "onCreate: Him " + idHim + " Me " + idMe);
            DatabaseReference myRef = database.getReference("Messages").child(idHim + "___" + idMe + "___" + messagesResponse.getAnnonce().getId()).child("discussion");
            myRef.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final ArrayList<Discussion> messagesResponses = new ArrayList<>();
                        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                              Discussion discussion1 = singleSnapshot.getValue(Discussion.class);
                              messagesResponses.add(discussion1);
                        }
                        Log.e(TAG, "onDataChange: Size = " + messagesResponses.size());
                        recyclerView.setAdapter(new MessageAdapterr());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        if (userMe.getClaims().getScopes().contains("PROMO")) {
                              messageAdapter = new MessageAdapterr(getApplicationContext(), messagesResponses, messagesResponse.getUser_me(), messagesResponse.getUser_him());

                        } else {
                              messageAdapter = new MessageAdapterr(getApplicationContext(), messagesResponses, messagesResponse.getUser_him(), messagesResponse.getUser_me());

                        }
                        recyclerView.setAdapter(messageAdapter);
                        progressdialog.dismiss();
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
            });
            toolbar.setTitle(messagesResponse.annonce.getTitle());
            text_send = findViewById(R.id.text_send);
            btn_send = findViewById(R.id.btn_send);
            btn_send.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (userMe.getClaims().getScopes().contains("PROMO")) {
                              sendMessage(messagesResponse.annonce, new Discussion(messagesResponse.getUser_me().getId(), messagesResponse.getUser_him().getId(), text_send.getText().toString(),
                                      (System.currentTimeMillis() / 100)), messagesResponse.getUser_him(), messagesResponse.getUser_me());

                        } else {
                              sendMessage(messagesResponse.annonce, new Discussion(messagesResponse.getUser_him().getId(), messagesResponse.getUser_me().getId(), text_send.getText().toString(),
                                      (System.currentTimeMillis() / 100)), messagesResponse.getUser_him(), messagesResponse.getUser_me());

                        }

                        text_send.setText("");
                  }
            });
      }

}
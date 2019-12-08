package com.example.projetimmo.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.projetimmo.MessagesResponse;
import com.example.projetimmo.MessagesResponseSend;
import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.itemToSell;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Utils {
      private static final String TAG = "Utils";

      public static void sendMessage(itemToSell itemToSell, Discussion discussion, User him, User me) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            String key = database.getReference("Messages").child(me.getId()+ "___" + him.getId() + "___" + itemToSell.getId()).child("discussion").push().getKey();
            DatabaseReference myRefSell = database.getReference("Messages").child(me.getId()+ "___" + him.getId() + "___" + itemToSell.getId()).child("annonce");
            DatabaseReference myRefHim = database.getReference("Messages").child(me.getId()+ "___" + him.getId() + "___" + itemToSell.getId()).child("userHim");
            DatabaseReference myRefMe = database.getReference("Messages").child(me.getId()+ "___" + him.getId() + "___" + itemToSell.getId()).child("userMe");
            DatabaseReference myRefDiscussion = database.getReference("Messages").child(me.getId()+ "___" + him.getId() + "___" + itemToSell.getId()).child("discussion").child(key);
            myRefSell.setValue(itemToSell);
            myRefHim.setValue(him);
            myRefMe.setValue(me);
            myRefDiscussion.setValue(discussion);
      }

      public static ArrayList<MessagesResponse> readMessage(MessagesResponse discussion) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Messages").child(discussion.getUser_me().getId() + "___" + discussion.getUser_him().getId() + "___" + discussion.getAnnonce().getId());
            final ArrayList<MessagesResponse> messagesResponses = new ArrayList<>();
            myRef.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                              messagesResponses.add(singleSnapshot.getValue(MessagesResponse.class));

                        }
                        Log.e(TAG, "onDataChange: Done");
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled: Done");
                  }
            });
            return messagesResponses;

      }
}

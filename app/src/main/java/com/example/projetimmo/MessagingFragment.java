package com.example.projetimmo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetimmo.Apdaters.DiscussionAdapter;
import com.example.projetimmo.Apdaters.MessageAdapterr;
import com.example.projetimmo.Apdaters.UserAdapter;
import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.utils.TinyDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagingFragment extends Fragment {

      private static final String TAG = "MessagingFragment";
      String idcurrent = "none";
      Discussion dic;
      private RecyclerView recyclerView;
      private UserAdapter userAdapter;
      private MessageAdapterr msgAdapter;
      private UserResponse userMe;
      private User userHim;
      private TinyDB tinydb;
      private itemToSell itemToSell;
      MessagesResponse messagesResponse;

      public MessagingFragment() {
      }


      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_messaging, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            tinydb = new TinyDB(getContext());
            userMe = tinydb.getObject("user", UserResponse.class);
            itemToSell = tinydb.getObject("selectedItem", itemToSell.class);
            userHim = tinydb.getObject("user_him", User.class);
            recyclerView = view.findViewById(R.id.recycleViewid);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Messages");
            final ArrayList<MessagesResponse> messagesResponses = new ArrayList<>();
            final ProgressDialog progressdialog = new ProgressDialog(getContext());
            progressdialog.setMessage("Please Wait....");
            progressdialog.show();
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                              if ((snapshot.getKey().contains(userMe.getUser().getId() + ""))) {
                                    messagesResponse = new MessagesResponse();
                                    itemToSell itemToSell2 = snapshot.child("annonce").getValue(com.example.projetimmo.Models.itemToSell.class);
                                    User userHimFirebase = snapshot.child("userHim").getValue(User.class);
                                    User userMeFirebase = snapshot.child("userMe").getValue(User.class);
                                    messagesResponse.setUser_me(userMeFirebase);
                                    messagesResponse.setUser_him(userHimFirebase);
                                    messagesResponse.setAnnonce(itemToSell2);
                                    messagesResponse.setDiscussion(new ArrayList<Discussion>());
                                    ArrayList<Discussion> discussions = new ArrayList<>();
                                    for (DataSnapshot snapshot1 : snapshot.child("discussion").getChildren()) {
                                          Discussion hashMaps = snapshot1.getValue(Discussion.class);
                                          discussions.add(hashMaps);
                                    }
                                    messagesResponse.setDiscussion(discussions);
                                    messagesResponses.add(messagesResponse);

                              }
                        }
                        DiscussionAdapter discussionAdapter = new DiscussionAdapter(getContext(), messagesResponses);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        recyclerView.setAdapter(discussionAdapter);
                        progressdialog.dismiss();
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled: Done");
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

}

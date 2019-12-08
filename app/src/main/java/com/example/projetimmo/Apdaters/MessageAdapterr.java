package com.example.projetimmo.Apdaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetimmo.MessagesResponse;
import com.example.projetimmo.Models.Chat;
import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MessageAdapterr extends RecyclerView.Adapter<MessageAdapterr.ViewHolder> {

      private Context mContext;
      private ArrayList<Discussion> mChats;
      private User me, him;
      String pattern = "HH:mm";

      public MessageAdapterr(Context mcontext, ArrayList<Discussion> mlist, User me, User him) {
            this.mContext = mcontext;
            this.mChats = mlist;
            this.me = me;
            this.him = him;
      }

      public MessageAdapterr() {
      }

      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chatroom_left, parent, false);
            return new MessageAdapterr.ViewHolder(view);
      }

      @Override
      public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Discussion discussion = mChats.get(position);
            Timestamp timestamp = new Timestamp(discussion.getTimestamp());
            Date date = new Date(timestamp.getTime());
            DateFormat df = new SimpleDateFormat(pattern);

            if (me.getId() == discussion.getId_sender()) {
                  holder.constSender.setVisibility(View.VISIBLE);
                  holder.constReceiver.setVisibility(View.GONE);
                  holder.showMessageSender.setText(discussion.getMessage());
                  holder.dateSender.setText(df.format(date));
                  Glide.with(mContext)
                          .load(me.getUrlProfilePicture())
                          .apply(new RequestOptions()
                                  .error(R.drawable.ic_close))
                          .into(holder.profile_imageReceiver);

            } else {
                  holder.constReceiver.setVisibility(View.VISIBLE);
                  holder.constSender.setVisibility(View.GONE);
                  holder.showMessageReceiver.setText(discussion.getMessage());
                  holder.dateReceiver.setText(df.format(date));
                  Glide.with(mContext)
                          .load(him.getUrlProfilePicture())
                          .apply(new RequestOptions()
                                  .error(R.drawable.ic_close))
                          .into(holder.profile_imageSender);
            }


      }


      @Override
      public int getItemCount() {
            return mChats.size();
      }

      public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView showMessageSender, showMessageReceiver;
            public TextView dateSender, dateReceiver;
            public ImageView profile_imageSender, profile_imageReceiver;
            ConstraintLayout constSender, constReceiver;

            public ViewHolder(View itemView) {
                  super(itemView);
                  showMessageSender = itemView.findViewById(R.id.showMsg);
                  showMessageReceiver = itemView.findViewById(R.id.showMsgReceiver);
                  dateSender = itemView.findViewById(R.id.textView2);
                  dateReceiver = itemView.findViewById(R.id.dateReceiver);
                  profile_imageSender = itemView.findViewById(R.id.profileImg);
                  profile_imageReceiver = itemView.findViewById(R.id.profileImgreceiver);
                  constSender = itemView.findViewById(R.id.senderConst);
                  constReceiver = itemView.findViewById(R.id.receiverConst);
            }
      }

}

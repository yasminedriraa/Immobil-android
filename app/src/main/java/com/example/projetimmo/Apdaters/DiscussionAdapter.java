package com.example.projetimmo.Apdaters;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetimmo.ChatRoomActivity;
import com.example.projetimmo.MessagesResponse;
import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.R;
import com.example.projetimmo.utils.TinyDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.ViewHolder> {
      private Context mContext;
      private List<MessagesResponse> mdiscussions;
      private ViewHolder holder;
      private static final String TAG = "DiscussionAdapter";

      public DiscussionAdapter(Context mcontext, ArrayList<MessagesResponse> messagesResponses) {
            this.mContext = mcontext;
            this.mdiscussions = messagesResponses;
      }

      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.discussion_item, parent, false);
            return new DiscussionAdapter.ViewHolder(view);
      }

      @Override
      public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            Timestamp timestamp = new Timestamp(mdiscussions.get(position).getDiscussion().get(mdiscussions.get(position).getDiscussion().size() - 1).getTimestamp());
            Date date = new Date(timestamp.getTime());
            String pattern = "HH:mm";
            DateFormat df = new SimpleDateFormat(pattern);
            holder.dateTv.setText(df.format(date));
            holder.username.setText(mdiscussions.get(position).getUser_him().getFirstName() + " " + mdiscussions.get(position).getUser_him().getLastName());
            holder.textMsg.setText(mdiscussions.get(position).getDiscussion().get(mdiscussions.get(position).getDiscussion().size() - 1).getMessage());
            Glide.with(mContext)
                    .load(mdiscussions.get(position).getUser_him().getUrlProfilePicture())
                    .apply(new RequestOptions()
                            .error(R.drawable.ic_close))
                    .into(holder.profile_image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        TinyDB tinyDB = new TinyDB(mContext);
                        tinyDB.putObject("the_conversation", mdiscussions.get(position));
                        Log.e(TAG, "onClick: " + mdiscussions.get(position).toString());
                        Intent intent = new Intent(mContext, ChatRoomActivity.class);
                        mContext.startActivity(intent);
                  }
            });
      }


      @Override
      public int getItemCount() {
            return mdiscussions.size();
      }


      public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView username, textMsg, dateTv;
            public ImageView profile_image;

            public ViewHolder(View itemView) {
                  super(itemView);
                  username = itemView.findViewById(R.id.usernameid);
                  dateTv = itemView.findViewById(R.id.date);
                  profile_image = itemView.findViewById(R.id.profileImg);
                  textMsg = itemView.findViewById(R.id.textMessage);

            }

      }
}






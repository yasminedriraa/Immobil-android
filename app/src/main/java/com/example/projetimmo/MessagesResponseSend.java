package com.example.projetimmo;

import androidx.annotation.Nullable;

import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.itemToSell;
import com.google.gson.annotations.SerializedName;

public class MessagesResponseSend {
      @SerializedName("annonce")
      itemToSell annonce;
      @SerializedName("discussion")
      Discussion discussion;
      @SerializedName("user_me")
      User user_me;
      @SerializedName("user_him")
      User user_him;

      public MessagesResponseSend() {
      }

      public MessagesResponseSend(itemToSell annonce, @Nullable Discussion discussion, User user_me, User user_him) {
            this.annonce = annonce;
            this.discussion = discussion;
            this.user_me = user_me;
            this.user_him = user_him;
      }

      public itemToSell getAnnonce() {
            return annonce;
      }

      public void setAnnonce(itemToSell annonce) {
            this.annonce = annonce;
      }

      public Discussion getDiscussion() {
            return discussion;
      }

      public void setDiscussion(Discussion discussion) {
            this.discussion = discussion;
      }

      public User getUser_me() {
            return user_me;
      }

      public void setUser_me(User user_me) {
            this.user_me = user_me;
      }

      public User getUser_him() {
            return user_him;
      }

      public void setUser_him(User user_him) {
            this.user_him = user_him;
      }

      @Override
      public String toString() {
            return "MessagesResponse{" +
                    "annonce=" + annonce +
                    ", discussion=" + discussion +
                    ", user_me=" + user_me +
                    ", user_him=" + user_him +
                    '}';
      }
}

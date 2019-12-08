package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class NotesRequest {
      @SerializedName("annonce")
      idObject id;
      @SerializedName("content")
      String content;
      @SerializedName("title")
      String title;
      @SerializedName("userId")
      int userId;

      public NotesRequest(idObject id, String content, String title, int userId) {
            this.id = id;
            this.content = content;
            this.title = title;
            this.userId = userId;
      }

      public idObject getStringIntegerHashMap() {
            return id;
      }

      public void setStringIntegerHashMap(idObject id) {
            this.id = id;
      }

      public String getContent() {
            return content;
      }

      public void setContent(String content) {
            this.content = content;
      }

      public String getTitle() {
            return title;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      public int getUserId() {
            return userId;
      }

      public void setUserId(int userId) {
            this.userId = userId;
      }

      @Override
      public String toString() {
            return "NotesRequest{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", title='" + title + '\'' +
                    ", userId=" + userId +
                    '}';
      }
}

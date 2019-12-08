package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class Notes {
      @SerializedName("id")
      int id;
      @SerializedName("title")
      String  title;
      @SerializedName("content")
      String content;
      @SerializedName("userId")
      int userId;

      public Notes() {
      }

      public Notes(int id, String title, String content, int userId) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.userId = userId;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getTitle() {
            return title;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      public String getContent() {
            return content;
      }

      public void setContent(String content) {
            this.content = content;
      }

      public int getUserId() {
            return userId;
      }

      public void setUserId(int userId) {
            this.userId = userId;
      }

      @Override
      public String toString() {
            return "Notes{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", userId=" + userId +
                    '}';
      }
}

package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FavoriteAnnonceResponse {
      @SerializedName("id")
      int id;
      @SerializedName("name")
      String name;
      @SerializedName("userId")
      int userId;
      @SerializedName("annonceSet")
      ArrayList<itemToSell> favoriteAnnonces;

      public FavoriteAnnonceResponse() {
      }

      public FavoriteAnnonceResponse(int id, String name, int userId, ArrayList<itemToSell> favoriteAnnonces) {
            this.id = id;
            this.name = name;
            this.userId = userId;
            this.favoriteAnnonces = favoriteAnnonces;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public int getUserId() {
            return userId;
      }

      public void setUserId(int userId) {
            this.userId = userId;
      }

      public ArrayList<itemToSell> getFavoriteAnnonces() {
            return favoriteAnnonces;
      }

      public void setFavoriteAnnonces(ArrayList<itemToSell> favoriteAnnonces) {
            this.favoriteAnnonces = favoriteAnnonces;
      }

      @Override
      public String toString() {
            return "FavoriteAnnonceResponse{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", userId=" + userId +
                    ", favoriteAnnonces=" + favoriteAnnonces +
                    '}';
      }
}

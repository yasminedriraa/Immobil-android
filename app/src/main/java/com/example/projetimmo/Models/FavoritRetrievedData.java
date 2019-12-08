package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FavoritRetrievedData {
      @SerializedName("id")
      int id;
      @SerializedName("name")
      String name;
      @SerializedName("userId")
      int userId;
      @SerializedName("annonceSet")
      ArrayList<itemToSell> itemToSells;

      public FavoritRetrievedData() {
      }

      public FavoritRetrievedData(int id, String name, int userId, ArrayList<itemToSell> itemToSells) {
            this.id = id;
            this.name = name;
            this.userId = userId;
            this.itemToSells = itemToSells;
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

      public ArrayList<itemToSell> getItemToSells() {
            return itemToSells;
      }

      public void setItemToSells(ArrayList<itemToSell> itemToSells) {
            this.itemToSells = itemToSells;
      }

      @Override
      public String toString() {
            return "FavoritRetrievedData{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", userId=" + userId +
                    ", itemToSells=" + itemToSells +
                    '}';
      }
}

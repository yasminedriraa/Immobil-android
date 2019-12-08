package com.example.projetimmo.Models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Categorie {
      @SerializedName("id")
      int id;
      @SerializedName("name")
      String name;

      public Categorie() {
      }

      public Categorie(int id, String name) {
            this.id = id;
            this.name = name;
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

      @Override
      public String toString() {
            return "Categorie{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
      }
}

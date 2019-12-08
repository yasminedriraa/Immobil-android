package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class SearchCriteriaRequest {
      @SerializedName("atout")
      private String atout;
      @SerializedName("prix_min")
      private int minPrice;
      @SerializedName("prix_max")
      private int maxPrice;
      @SerializedName("category")
      private Categorie category;
      @SerializedName("location")
      private  Location location;
      @SerializedName("surface_min")
      private int minSurface;
      @SerializedName("surface_max")
      private  int maxSurface;

      public SearchCriteriaRequest() {
      }

      public SearchCriteriaRequest(String atout, int minPrice, int maxPrice, Categorie category, Location location, int minSurface, int maxSurface) {
            this.atout = atout;
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            this.category = category;
            this.location = location;
            this.minSurface = minSurface;
            this.maxSurface = maxSurface;
      }

      public String getAtout() {
            return atout;
      }

      public void setAtout(String atout) {
            this.atout = atout;
      }

      public int getMinPrice() {
            return minPrice;
      }

      public void setMinPrice(int minPrice) {
            this.minPrice = minPrice;
      }

      public int getMaxPrice() {
            return maxPrice;
      }

      public void setMaxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
      }

      public Categorie getCategory() {
            return category;
      }

      public void setCategory(Categorie category) {
            this.category = category;
      }

      public Location getLocation() {
            return location;
      }

      public void setLocation(Location location) {
            this.location = location;
      }

      public int getMinSurface() {
            return minSurface;
      }

      public void setMinSurface(int minSurface) {
            this.minSurface = minSurface;
      }

      public int getMaxSurface() {
            return maxSurface;
      }

      public void setMaxSurface(int maxSurface) {
            this.maxSurface = maxSurface;
      }

      @Override
      public String toString() {
            return "SearchCriteriaRequest{" +
                    "atout='" + atout + '\'' +
                    ", minPrice='" + minPrice + '\'' +
                    ", maxPrice='" + maxPrice + '\'' +
                    ", category=" + category +
                    ", location=" + location +
                    ", minSurface=" + minSurface +
                    ", maxSurface=" + maxSurface +
                    '}';
      }
}

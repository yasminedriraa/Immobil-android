package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class Location {
      @SerializedName("id")
      int id;
      @SerializedName("pays")
      String country;
      @SerializedName("region")
      String region;
      @SerializedName("ville")
      String ville;
      @SerializedName("adresse")
      String address;

      public Location() {
      }

      public Location(int id, String country, String region, String ville, String address) {
            this.id = id;
            this.country = country;
            this.region = region;
            this.ville = ville;
            this.address = address;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getCountry() {
            return country;
      }

      public void setCountry(String country) {
            this.country = country;
      }

      public String getRegion() {
            return region;
      }

      public void setRegion(String region) {
            this.region = region;
      }

      public String getVille() {
            return ville;
      }

      public void setVille(String ville) {
            this.ville = ville;
      }

      public String getAddress() {
            return address;
      }

      public void setAddress(String address) {
            this.address = address;
      }

      @Override
      public String toString() {
            return "Location{" +
                    "id=" + id +
                    ", country='" + country + '\'' +
                    ", region='" + region + '\'' +
                    ", ville='" + ville + '\'' +
                    ", address='" + address + '\'' +
                    '}';
      }
}

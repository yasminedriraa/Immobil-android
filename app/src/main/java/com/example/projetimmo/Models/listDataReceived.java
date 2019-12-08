package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class listDataReceived {
      @SerializedName("numberBedrooms")
      int numberBedrooms;
      @SerializedName("totalNumber")
      int numberHouses;
      @SerializedName("availableNumber")
      int availableHouses;
      @SerializedName("price")
      double totalPrice;

      public listDataReceived() {
      }

      public listDataReceived(int numberBedrooms, int numberHouses, int availableHouses, double totalPrice) {
            this.numberBedrooms = numberBedrooms;
            this.numberHouses = numberHouses;
            this.availableHouses = availableHouses;
            this.totalPrice = totalPrice;
      }

      public int getNumberBedrooms() {
            return numberBedrooms;
      }

      public void setNumberBedrooms(int numberBedrooms) {
            this.numberBedrooms = numberBedrooms;
      }

      public int getNumberHouses() {
            return numberHouses;
      }

      public void setNumberHouses(int numberHouses) {
            this.numberHouses = numberHouses;
      }

      public int getAvailableHouses() {
            return availableHouses;
      }

      public void setAvailableHouses(int availableHouses) {
            this.availableHouses = availableHouses;
      }

      public double getTotalPrice() {
            return totalPrice;
      }

      public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
      }

      @Override
      public String toString() {
            return "listDataReceived{" +
                    "numberBedrooms=" + numberBedrooms +
                    ", numberHouses=" + numberHouses +
                    ", availableHouses=" + availableHouses +
                    ", totalPrice=" + totalPrice +
                    '}';
      }
}

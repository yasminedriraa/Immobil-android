package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class objectPercentage {
      @SerializedName("numberBedrooms")
      int numberBedrooms;
      @SerializedName("percentage")
      float percentage;

      public objectPercentage() {
      }

      public objectPercentage(int numberBedrooms, int percentage) {
            this.numberBedrooms = numberBedrooms;
            this.percentage = percentage;
      }

      public int getNumberBedrooms() {
            return numberBedrooms;
      }

      public void setNumberBedrooms(int numberBedrooms) {
            this.numberBedrooms = numberBedrooms;
      }

      public float getPercentage() {
            return percentage;
      }

      public void setPercentage(float percentage) {
            this.percentage = percentage;
      }

      @Override
      public String toString() {
            return "objectPercentage{" +
                    "numberBedrooms=" + numberBedrooms +
                    ", percentage=" + percentage +
                    '}';
      }
}

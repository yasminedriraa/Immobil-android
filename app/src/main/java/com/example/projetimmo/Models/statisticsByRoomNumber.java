package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class statisticsByRoomNumber {
      @SerializedName("statDTOS")
      ArrayList<listDataReceived> listDataReceiveds;
      @SerializedName("objectPercentage")
      ArrayList<objectPercentage> objectPercentages;

      public statisticsByRoomNumber() {
      }

      public statisticsByRoomNumber(ArrayList<listDataReceived> listDataReceiveds, ArrayList<objectPercentage> objectPercentages) {
            this.listDataReceiveds = listDataReceiveds;
            this.objectPercentages = objectPercentages;
      }

      public ArrayList<listDataReceived> getListDataReceiveds() {
            return listDataReceiveds;
      }

      public void setListDataReceiveds(ArrayList<listDataReceived> listDataReceiveds) {
            this.listDataReceiveds = listDataReceiveds;
      }

      public ArrayList<objectPercentage> getObjectPercentages() {
            return objectPercentages;
      }

      public void setObjectPercentages(ArrayList<objectPercentage> objectPercentages) {
            this.objectPercentages = objectPercentages;
      }

      @Override
      public String toString() {
            return "statisticsByRoomNumber{" +
                    "listDataReceiveds=" + listDataReceiveds +
                    ", objectPercentages=" + objectPercentages +
                    '}';
      }
}

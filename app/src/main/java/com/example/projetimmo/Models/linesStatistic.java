package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class linesStatistic {
      @SerializedName("monthIndex")
      int monthIndex;
      @SerializedName("incomesMonthIndex")
      int incomesMonthIndex;

      public linesStatistic() {
      }

      public linesStatistic(int monthIndex, int incomesMonthIndex) {
            this.monthIndex = monthIndex;
            this.incomesMonthIndex = incomesMonthIndex;
      }

      public int getMonthIndex() {
            return monthIndex;
      }

      public void setMonthIndex(int monthIndex) {
            this.monthIndex = monthIndex;
      }

      public int getIncomesMonthIndex() {
            return incomesMonthIndex;
      }

      public void setIncomesMonthIndex(int incomesMonthIndex) {
            this.incomesMonthIndex = incomesMonthIndex;
      }

      @Override
      public String toString() {
            return "linesStatistic{" +
                    "monthIndex=" + monthIndex +
                    ", incomesMonthIndex=" + incomesMonthIndex +
                    '}';
      }
}

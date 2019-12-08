package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class linesStatistics {
      @SerializedName("linesStatistics")
      ArrayList<linesStatistic> linesStatistics;
      public linesStatistics() {
      }
      public linesStatistics(ArrayList<linesStatistic> linesStatistics) {
            this.linesStatistics = linesStatistics;
      }

      public ArrayList<linesStatistic> getLinesStatistics() {
            return linesStatistics;
      }

      public void setLinesStatistics(ArrayList<linesStatistic> linesStatistics) {
            this.linesStatistics = linesStatistics;
      }

      @Override
      public String toString() {
            return "linesStatistics{" +
                    "linesStatistics=" + linesStatistics +
                    '}';
      }
}

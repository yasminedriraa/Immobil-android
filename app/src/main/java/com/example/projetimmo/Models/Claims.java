package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class Claims {
      @SerializedName("sub")
      String sub;
      @SerializedName("scopes")
      String scopes;

      public Claims() {
      }

      public Claims(String sub, String scopes) {
            this.sub = sub;
            this.scopes = scopes;
      }

      public String getSub() {
            return sub;
      }

      public void setSub(String sub) {
            this.sub = sub;
      }

      public String getScopes() {
            return scopes;
      }

      public void setScopes(String scopes) {
            this.scopes = scopes;
      }

      @Override
      public String toString() {
            return "Claims{" +
                    "sub='" + sub + '\'' +
                    ", scopes='" + scopes + '\'' +
                    '}';
      }
}

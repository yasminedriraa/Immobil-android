package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
      @SerializedName("user")
      User user;
      @SerializedName("claims")
      Claims claims;
      @SerializedName("token")
      String token;

      public UserResponse() {
      }

      public UserResponse(User user, Claims claims, String token) {
            this.user = user;
            this.claims = claims;
            this.token = token;
      }

      public User getUser() {
            return user;
      }

      public void setUser(User user) {
            this.user = user;
      }

      public Claims getClaims() {
            return claims;
      }

      public void setClaims(Claims claims) {
            this.claims = claims;
      }

      public String getToken() {
            return token;
      }

      public void setToken(String token) {
            this.token = token;
      }

      @Override
      public String toString() {
            return "UserResponse{" +
                    "user='" + user + '\'' +
                    ", claims=" + claims +
                    ", token='" + token + '\'' +
                    '}';
      }
}

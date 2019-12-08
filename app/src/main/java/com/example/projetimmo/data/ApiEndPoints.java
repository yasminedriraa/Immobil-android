package com.example.projetimmo.data;

public class ApiEndPoints {
      public final static String BASE_URL = "http://192.168.1.10:8080/";


      //ACCOUNT
      public final static String LOGIN = "authenticate";
      public final static String SIGN_UP = "register";
      public final static String UPDATE_PROFILE= "api/v1/update-profile";
      public final static String GET_USER_BY_ID= "user";
      public final static String RESET_PASSWORD= "user/resetPassword";

      //ANNONCE
      public final static String GET_ANNONCES_BY_CATEGORY = "api/v1/annonces/category/{id}";
      public final static String GET_ANNONCES_BY_FILTER = "api/v1/annonces/search";
      public final static String POST_ANNONCES = "api/v1/create-annonce";
      public final static String DEL_ANNONCES = "api/v1/delete-annonce/{id}";
      public final static String GET_ANNONCES = "api/v1/getAllAnnonce";
      public final static String GET_ANNONCESBYUSERID = "api/v1/annonce/userId";
      public final static String GET_ANNONCES_BY_ID = "api/v1/get-annonce-byID/{id}";
      public final static String UPDATE_ANNONCES_BY_ID = "api/v1/update-annonce";
      public final static String GET_FAVORITE_ANNONCES = "api/v1/getAllAnnonce/{id}";
      public final static String MAKE_THE_ANNONCE_SOLD= "api/v1/annonce/sell";

      public final static String GET_THE_STATISTICS_BY_ROOM_NUMBER= "api/v1/annonce/statistics";
      public final static String GET_THE_STATISTICS_BY_DATE= "api/v1/annonce/statistics/monthly";

      //FAVORIT
      public final static String GET_FAVORITE_ANNONCES_BY_ID_USER = "api/v1/favorisById";
      public final static String POST_FAVORITE = "api/v1/favoris";
      public final static String DEL_FAVORITE_ANNONCES = "api/v1/deleteFromFavoris";

      //CATEGORIE
      public final static String GET_ALL_CATEGORIES = "api/v1/categories";
      public final static String POST_CATEGORIES = "api/v1/categories";
      public final static String DEL_CATEGORIES = "api/v1/categories/{id}";
      public final static String GET_CATEGORIES_BY_ID = "api/v1/categories/{id}";
      public final static String PUT_CATEGORIES_BY_ID = "api/v1/categories/{id}";

      //LOCATION
      public final static String GET_ALL_LOCATIONS = "api/v1/location";

      //NOTES
      public final static String GET_NOTES_BY_USER_ID = "api/v1/notesByUserId/";
      public final static String GET_NOTES_ANNONCE_ID = "api/v1/notes/annonce/{annonceId}";
      public final static String POST_NOTES_BY_USER = "api/v1/notes/";
      public final static String DEL_NOTES_BY_USER = "api/v1/notes";
      public final static String PUT_NOTES_BY_USER = "api/v1/notes";

}

package com.example.projetimmo.data;

import com.example.projetimmo.Models.FavoritRetrievedData;
import com.example.projetimmo.Models.FavoriteAnnonceResponse;
import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.NotesRequest;
import com.example.projetimmo.Models.SearchCriteriaRequest;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.Models.linesStatistic;
import com.example.projetimmo.Models.linesStatistics;
import com.example.projetimmo.Models.statisticsByRoomNumber;
import com.example.projetimmo.login.LoginRequest;
import com.example.projetimmo.register.RegisterRequestData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ImmoApi {
      @POST(ApiEndPoints.LOGIN)
      @Headers(
              "Content-type: application/json")
      Call<UserResponse> login(@Body LoginRequest request);

      @POST(ApiEndPoints.SIGN_UP)
      Call<ResponseBody> register(@Body RegisterRequestData request);

      @GET(ApiEndPoints.GET_ANNONCESBYUSERID)
      Call<List<itemToSell>> getAnnonceByUserID(@Query("userId") int id, @Header("Authorization") String token);

      @GET(ApiEndPoints.MAKE_THE_ANNONCE_SOLD)
      Call<ResponseBody> makeTheAnnonceSold(@Query("annonceId") int id, @Header("Authorization") String token);

      @GET(ApiEndPoints.GET_ANNONCES)
      Call<List<itemToSell>> getAllAnnonce(@Header("Authorization") String token);

      @GET(ApiEndPoints.GET_FAVORITE_ANNONCES)
      Call<FavoriteAnnonceResponse> getFavoritAnnonce(@Query("username") int id_user);

      @PUT(ApiEndPoints.DEL_FAVORITE_ANNONCES)
      Call<ResponseBody> delFavoritAnnonce(@Query("id") int id_annonce, @Query("id_user") int id_user, @Header("Authorization") String token);

      @PUT(ApiEndPoints.POST_FAVORITE)
      Call<ResponseBody> postFavoritAnnonce(@Query("id") int id_annonce, @Query("id_user") int id_user, @Header("Authorization") String token);

      @GET(ApiEndPoints.GET_FAVORITE_ANNONCES_BY_ID_USER)
      Call<FavoritRetrievedData> getFavoritAnnonceByIdUser(@Query("id") int id_annonce, @Header("Authorization") String token);

      @GET(ApiEndPoints.GET_NOTES_BY_USER_ID)
      Call<List<Notes>> getNotesByUserId(@Query("user_id") int id_annonce, @Header("Authorization") String token);

      @POST(ApiEndPoints.POST_NOTES_BY_USER)
      Call<ResponseBody> postNotes(@Body NotesRequest notes, @Header("Authorization") String token);

      @POST(ApiEndPoints.RESET_PASSWORD)
      Call<ResponseBody> recoverPassword(@Query("email") String email);

      @POST(ApiEndPoints.POST_ANNONCES)
      Call<ResponseBody> postAnnonces(@Body itemToSell itemToSell, @Header("Authorization") String token);

      @DELETE(ApiEndPoints.DEL_NOTES_BY_USER)
      Call<ResponseBody> deleteNotes(@Query("id_note") int id_note, @Header("Authorization") String token);

      @PUT(ApiEndPoints.PUT_NOTES_BY_USER)
      Call<ResponseBody> putNotes(@Body Notes notes, @Header("Authorization") String token);

      @PUT(ApiEndPoints.UPDATE_PROFILE)
      Call<User> updateProfile(@Body User user, @Header("Authorization") String token);

      @PUT(ApiEndPoints.UPDATE_ANNONCES_BY_ID)
      Call<itemToSell> updateAnnonce(@Body itemToSell itemToSell, @Header("Authorization") String token);

      @GET(ApiEndPoints.GET_USER_BY_ID)
      Call<User> getUserByID(@Query("user_id") int user_id, @Header("Authorization") String token);

      @GET(ApiEndPoints.GET_THE_STATISTICS_BY_ROOM_NUMBER)
      Call<statisticsByRoomNumber> getTheStatisticsByRoomNumber(@Query("ownerId") int user_id, @Header("Authorization") String token);

      @GET(ApiEndPoints.GET_THE_STATISTICS_BY_DATE)
      Call<linesStatistics> getTheStatisticsByDate(@Query("ownerId") int user_id, @Header("Authorization") String token);

      @POST(ApiEndPoints.GET_ANNONCES_BY_FILTER)
      Call<List<itemToSell>> getAnnoncesByFilter(@Body SearchCriteriaRequest searchCriteriaRequest, @Header("Authorization") String token);
}

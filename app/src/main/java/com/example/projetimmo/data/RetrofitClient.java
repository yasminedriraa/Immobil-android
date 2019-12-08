package com.example.projetimmo.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static ImmoApi build(){
        return new Retrofit.Builder()
                .baseUrl(ApiEndPoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ImmoApi.class);
    }
}

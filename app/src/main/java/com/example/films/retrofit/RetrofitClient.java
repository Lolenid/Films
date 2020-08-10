package com.example.films.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://s3-eu-west-1.amazonaws.com/";
    private static RetrofitClient instanse;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(instanse == null){
            instanse = new RetrofitClient();
        }
        return instanse;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}

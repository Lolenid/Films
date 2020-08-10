package com.example.films.retrofit;

import com.example.films.models.Films;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("sequeniatesttask/films.json")
    Call<Films> getFilms();
}

package com.example.films;

import com.example.films.models.Film;
import com.example.films.models.Films;
import com.example.films.retrofit.RetrofitClient;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;


public class ExampleUnitTest {
    List<Film> films;
    @Test
    public void get_films() {
        RetrofitClient.getInstance().getApi().getFilms().enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                films = response.body().getFilms();
                assertEquals(17, films.size());
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {

            }
        });
    }
}
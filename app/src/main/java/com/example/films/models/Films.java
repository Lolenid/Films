package com.example.films.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films {
    @SerializedName("films")
    private List<Film> films;

    public List<Film> getFilms() {
        return films;
    }
}

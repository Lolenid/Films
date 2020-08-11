package com.example.films;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.films.adapters.FilmAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
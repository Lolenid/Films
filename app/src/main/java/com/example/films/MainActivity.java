package com.example.films;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.films.adapters.FilmAdapter;
import com.example.films.fragments.FilmDetailFragment;
import com.example.films.fragments.FilmsFragment;
import com.example.films.models.Film;
import com.example.films.models.Films;

public class MainActivity extends AppCompatActivity {

    private Film film;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


    }
    public void replaceFragment(final Fragment fragment, Film film){

        if(fragment!= null){
            if (fragment instanceof FilmDetailFragment){
                toolbar.setTitle(R.string.app_name);
            }else if(fragment instanceof FilmsFragment){
                toolbar.setTitle(film.getLocalizedName());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        Log.d("MyTag", "Click home toolbar");
                    }
                });
            }
            else {
                toolbar.setTitle("another");
            }
        }else {
            toolbar.setTitle("null");
        }
        this.film = film;
        FilmDetailFragment f1 = FilmDetailFragment.newInstance(film);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.filmsContainer, f1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
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
                FilmsFragment f1 = new FilmsFragment();
                Log.d("MyTag", "if");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.filmsContainer, f1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(fragment instanceof FilmsFragment){
                Log.d("MyTag", "else if");
                toolbar.setTitle(film.getLocalizedName());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        Log.d("MyTag", "setOnClickListener");
                    }
                });
                FilmDetailFragment f1 = FilmDetailFragment.newInstance(film);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.filmsContainer, f1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            else {
                toolbar.setTitle("another");
                Log.d("MyTag", "another");
            }
        }else {
            toolbar.setTitle("null");
            Log.d("MyTag", "null");
        }
        this.film = film;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("MyTag", "Click home toolbar");
    }
}
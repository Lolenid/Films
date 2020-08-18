package com.example.films.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.films.R;
import com.example.films.models.Film;


public class FilmDetailFragment extends Fragment {

    public FilmDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_films, container, false);

        ImageView filmPoster = view.findViewById(R.id.detailImage);
        TextView filmName = view.findViewById(R.id.detailName);
        TextView filmYear = view.findViewById(R.id.detailYear);
        TextView filmRating = view.findViewById(R.id.detailRating);
        TextView filmDescription = view.findViewById(R.id.detailDescription);

        Film film = FilmDetailFragmentArgs.fromBundle(requireArguments()).getDetailFilmArgument();

        Glide.with(getContext())
                .load(film.getImageUrl())
                .error(Glide.with(filmPoster).load(R.drawable.nothing))
                .into(filmPoster);
        filmName.setText(film.getLocalizedName());
        filmYear.setText(film.getYear().toString());
        filmRating.setText(film.getRating().toString());
        filmDescription.setText(film.getDescription());
        return view;
    }
}
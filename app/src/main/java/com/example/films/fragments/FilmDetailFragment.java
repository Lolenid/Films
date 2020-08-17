package com.example.films.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
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

import java.net.UnknownHostException;
import java.util.Objects;


public class FilmDetailFragment extends Fragment {

    public FilmDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_films, container, false);

        ImageView directionsImageView = view.findViewById(R.id.detailImage);
        TextView directionsTextView = view.findViewById(R.id.detailName);

        Film film = FilmDetailFragmentArgs.fromBundle(requireArguments()).getDetailFilmArgument();

        Glide.with(getContext())
                .load(film.getImageUrl())
                .error(Glide.with(directionsImageView).load(R.drawable.nothing))
                .into(directionsImageView);
        directionsTextView.setText(film.getLocalizedName());
        return view;
    }
}
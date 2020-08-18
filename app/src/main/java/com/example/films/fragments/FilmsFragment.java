package com.example.films.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.films.R;
import com.example.films.adapters.FilmAdapter;
import com.example.films.models.Film;
import com.example.films.models.Films;
import com.example.films.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment {
    private RecyclerView recyclerView;

    private List<Film> films;

//    private FilmAdapter filmAdapter;
//    private GridLayoutManager gridLayoutManager;

    public FilmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.films_fragment, container, false);
        recyclerView = view.findViewById(R.id.filmsRecycler);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        FilmAdapter navigatorAdapter = new FilmAdapter(films, getContext(), new FilmListener());
        recyclerView.setAdapter(navigatorAdapter);
    }

    private void loadData() {
        films = new ArrayList<>();
        RetrofitClient.getInstance().getApi().getFilms().enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.body() != null) {
                    films.addAll(response.body().getFilms());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), R.string.toast_text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    private class FilmListener implements FilmAdapter.Listener {


        @Override
        public void onFilmSelected(Film film, View view) {
            NavDirections action = FilmsFragmentDirections.actionFilmsFragmentToFilmDetailFragment(film, film.getLocalizedName());
            Navigation.findNavController(view).navigate(action);
        }
    }
}
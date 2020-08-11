package com.example.films.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    private FilmAdapter filmAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Film> films;

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
        films = new ArrayList<>();
        recyclerView = (RecyclerView) (RecyclerView) inflater.inflate(
                R.layout.films_fragment, container, false);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        filmAdapter = new FilmAdapter(films, getContext());
        recyclerView.setAdapter(filmAdapter);

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

        filmAdapter.setListener(new FilmAdapter.Listener() {
            public void onClick(int position) {
                FilmDetailFragment f1 = FilmDetailFragment.newInstance(films.get(position));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.conteiner, f1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return recyclerView;
    }
}
package com.example.films.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.films.R;
import com.example.films.models.Film;

import org.w3c.dom.Text;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private List<Film> films;
    private Context context;
    private Listener listener;

    public interface Listener{
        void onFilmSelected(Film film, View view);
    }
    public FilmAdapter(List<Film> films, Context context, Listener listener) {
        this.films = films;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Film film = films.get(position);
        holder.filmText.setText(film.getLocalizedName());

        Glide.with(context)
                .load(films.get(position).getImageUrl())
                .error(Glide.with(holder.filmImage).load(R.drawable.nothing))
                .into(holder.filmImage);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView filmImage;
        private TextView filmText;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            filmImage = itemView.findViewById(R.id.filmCardImage);
            filmText = itemView.findViewById(R.id.filmCardName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFilmSelected(films.get(getAdapterPosition()), itemView);
                }
            });
        }
    }

}

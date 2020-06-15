package com.example.projectpopularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    List<Movie> movies;

    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{
        void onClick(Movie clickedOnMovie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.movie_layout;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie forPoster = movies.get(position);
        String getThumbnail = forPoster.getPoster_path();
        String posterPath = "https://image.tmdb.org/t/p/w185/" + getThumbnail;
        Picasso.get().load(posterPath).into(holder.movie_Image);
    }

    @Override
    public int getItemCount() {
        return (movies!=null) ? movies.size():0;

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView movie_Image;

        MovieViewHolder(View itemView){
            super(itemView);
            movie_Image = (ImageView) itemView.findViewById(R.id.movieImage);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie clickedOnMovie = movies.get(adapterPosition);
            mClickHandler.onClick(clickedOnMovie);
        }
    }
    public void setMovieData(List<Movie> movieData){
        movies = movieData;
        notifyDataSetChanged();
    }

}

package com.example.projectpopularmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailerResult {

    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<MovieTrailer> trailer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieTrailer> getTrailer() {
        return trailer;
    }

    public void setMovieTrailer(List<MovieTrailer> movieTrailer) {
        this.trailer = movieTrailer;
    }
}

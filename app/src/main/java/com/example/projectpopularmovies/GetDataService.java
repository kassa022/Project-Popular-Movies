package com.example.projectpopularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetDataService {

    @GET("/3/movie/popular?api_key=")
    Call<MovieResult> getPopularMovies();

    @GET("/3/movie/top_rated?api_key=")
    Call<MovieResult> getTopRatedMovies();


    @GET("/3/movie/{id}/videos?api_key=")
    Call<MovieTrailerResult> getTrailerKey(@Path("id") int movieId);
}

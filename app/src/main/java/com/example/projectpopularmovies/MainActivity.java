package com.example.projectpopularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {


    private MovieAdapter mAdapter;
    private RecyclerView movieRecycler;

    List<Movie> movies = new ArrayList<>();

    private Call<MovieResult> displaySortedMenuItem;
    private GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecycler = (RecyclerView) findViewById(R.id.movieRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieRecycler.setLayoutManager(gridLayoutManager);
        mAdapter = new MovieAdapter(this);
        movieRecycler.setAdapter(mAdapter);

        //GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        displaySortedMenuItem = service.getPopularMovies();
        displayMovies(displaySortedMenuItem);
    }


    public void displayMovies(Call<MovieResult> sortByThis){
        Call<MovieResult> call = sortByThis;
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult body = response.body();
                List<Movie> movies =  body.getMovies();

                mAdapter.setMovieData(movies);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(Movie clickedOnMovie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);

        intentToStartDetailActivity.putExtra("Movie", clickedOnMovie);
        startActivity(intentToStartDetailActivity);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.most_popular){
            mAdapter.setMovieData(null);
            displaySortedMenuItem = service.getPopularMovies();
            displayMovies(displaySortedMenuItem);
        }
        if(id == R.id.top_rated){
            mAdapter.setMovieData(null);
            displaySortedMenuItem = service.getTopRatedMovies();
            displayMovies(displaySortedMenuItem);
        }
        return super.onOptionsItemSelected(item);
    }
}

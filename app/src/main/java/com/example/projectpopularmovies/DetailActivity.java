package com.example.projectpopularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private Movie passedInMovie;

    private ImageView detailImage;
    private TextView detailTitle;
    private TextView detailOverview;
    private TextView detailVoteAverage;
    private TextView detailReleaseDate;

    private ImageView detailTrailer;

    private String trailerName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen);

        detailImage = (ImageView) findViewById(R.id.clickedMovieImage);
        detailVoteAverage = (TextView) findViewById(R.id.userRating);


        detailTitle = (TextView) findViewById(R.id.originalTitle);
        detailOverview = (TextView) findViewById(R.id.synopsis);
        detailReleaseDate = (TextView) findViewById(R.id.releaseDate);

        detailTrailer = (ImageView) findViewById(R.id.trailerButton);
        detailTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(trailerName);
            }
        });

        Intent intentThatStartThisActivity = getIntent();

        if (intentThatStartThisActivity!= null){
            if(intentThatStartThisActivity.hasExtra("Movie")){
                passedInMovie = (Movie) intentThatStartThisActivity.getSerializableExtra("Movie");

                String getThumbnail = passedInMovie.getPoster_path();
                String posterPath = "https://image.tmdb.org/t/p/w185/" + getThumbnail;
                Picasso.get().load(posterPath).into(detailImage);


                detailTitle.setText(passedInMovie.getTitle());
                detailOverview.setText(trailerName);
                //detailOverview.setText(passedInMovie.getOverview());
                detailVoteAverage.setText(String.valueOf(passedInMovie.getVote_average()));
                detailReleaseDate.setText(passedInMovie.getRelease_date());

            }
        }
        getKey();

    }

    public void getKey(){

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MovieTrailerResult> call = service.getTrailerKey(passedInMovie.getId());
        call.enqueue(new Callback<MovieTrailerResult>() {
            @Override
            public void onResponse(Call<MovieTrailerResult> call, Response<MovieTrailerResult> response) {
                MovieTrailerResult body = response.body();
                List<MovieTrailer> trailer = body.getTrailer();
                String key = trailer.


            }
            @Override
            public void onFailure(Call<MovieTrailerResult> call, Throwable t) {

            }
        });


    }



    private void openWebPage(String trailerKey){
        String finalUrl = "https://youtube.com/watch?v=" + trailerKey;
        Uri webpage = Uri.parse(finalUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}

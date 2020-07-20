package com.akshay.tmdbclient.view;

import android.content.Intent;
import android.os.Bundle;

import com.akshay.tmdbclient.databinding.ActivityMovieBinding;
import com.akshay.tmdbclient.model.Movie;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.tmdbclient.R;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ImageView movieImage;
    private String image;
    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    private ActivityMovieBinding activityMovieBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");

            activityMovieBinding.setMovie(movie);

            image = movie.getPosterPath();

            getSupportActionBar().setTitle(movie.getTitle());


        }


    }
}
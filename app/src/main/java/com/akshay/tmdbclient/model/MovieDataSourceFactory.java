package com.akshay.tmdbclient.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.akshay.tmdbclient.service.MovieDataService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieDataService movieDataService;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    @NonNull
    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource(movieDataService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}

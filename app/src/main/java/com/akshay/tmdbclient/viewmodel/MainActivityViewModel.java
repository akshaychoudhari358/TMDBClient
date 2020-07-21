package com.akshay.tmdbclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.akshay.tmdbclient.model.Movie;
import com.akshay.tmdbclient.model.MovieDataSource;
import com.akshay.tmdbclient.model.MovieDataSourceFactory;
import com.akshay.tmdbclient.model.MovieRepository;
import com.akshay.tmdbclient.service.MovieDataService;
import com.akshay.tmdbclient.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);

        movieDataSourceLiveData= factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                                    .setEnablePlaceholders(true)
                                     .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory, config))
                    .setFetchExecutor(executor)
                .build();

    }

    public LiveData<List<Movie>> getAllMovie(){
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }
}

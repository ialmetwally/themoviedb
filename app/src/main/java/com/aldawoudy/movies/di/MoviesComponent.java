package com.aldawoudy.movies.di;

import com.aldawoudy.movies.api.ApiManager;
import com.aldawoudy.movies.api.MovieDBApi;
import com.aldawoudy.movies.data.source.MoviesRepository;
import com.aldawoudy.movies.moviedetail.MovieDetailFragment;
import com.aldawoudy.movies.movies.MoviesFragment;
import com.aldawoudy.movies.utils.schedulers.BaseSchedulerProvider;
import com.squareup.picasso.Picasso;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Ismail on 10/27/16.
 */
@Singleton
@Component(modules = {MoviesModule.class})
public interface MoviesComponent {

  ApiManager provideApiManager();

  MovieDBApi provideMovieDBApi();

  MoviesRepository provideMoviesRepository();

  Picasso providePicasso();

  BaseSchedulerProvider provideSchedulerProvider();

  void inject(MoviesFragment moviesFragment);

  void inject(MovieDetailFragment movieDetailFragment);
}

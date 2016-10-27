package com.aldawoudy.movies.data.source;

import android.support.annotation.NonNull;
import com.aldawoudy.movies.api.MovieDBApi;
import com.aldawoudy.movies.api.model.MovieModel;
import com.aldawoudy.movies.api.model.MoviesResponse;
import com.aldawoudy.movies.data.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Ismail on 10/27/16.
 */
@Singleton
public class MoviesRepository implements MoviesDataSource {

  private final MovieDBApi mApi;

  private int mPage = 0;
  private int mTotalPages = 0;
  private int mNextPage = -1;

  public MoviesRepository(MovieDBApi api) {
    mApi = api;
  }

  @Override
  public Observable<List<Movie>> getPopularMovies(@NonNull Integer page) {
    mPage = page;
    return mApi.getPopularMovies(mPage).flatMap(this::handleMoviesResponse);
  }

  private Observable<List<Movie>> handleMoviesResponse(Response<MoviesResponse> response) {
    if (response.isSuccessful()) {
      MoviesResponse moviesResponse = response.body();
      mTotalPages = moviesResponse.getTotalPages();
      if (hasMore()) {
        mNextPage = mPage + 1;
      } else {
        mNextPage = -1;
      }
      return Observable.just(mapResponse(moviesResponse.getResults()));
    } else {
      // FIXME: 10/27/16 return a proper exception
      return Observable.error(new Throwable());
    }
  }

  private Observable<Movie> handleMovieResponse(Response<MovieModel> response) {
    if (response.isSuccessful()) {
      return Observable.just(new Movie(response.body()));
    } else {
      // FIXME: 10/27/16 return a proper exception
      return Observable.error(new Throwable());
    }
  }

  private List<Movie> mapResponse(List<MovieModel> results) {
    List<Movie> movies = new ArrayList<>();
    for (MovieModel movieModel : results) {
      movies.add(new Movie(movieModel));
    }
    return movies;
  }

  @Override
  public Observable<List<Movie>> getNowPlayingMovies(@NonNull Integer page) {
    mPage = page;
    return mApi.getNowPlayingMovies(mPage).flatMap(this::handleMoviesResponse);
  }

  @Override
  public Observable<List<Movie>> getTopRatedMovies(@NonNull Integer page) {
    mPage = page;
    return mApi.getTopRatedMovies(mPage).flatMap(this::handleMoviesResponse);
  }

  @Override
  public Observable<List<Movie>> getUpcomingMovies(@NonNull Integer page) {
    mPage = page;
    return mApi.getUpcomingMovies(mPage).flatMap(this::handleMoviesResponse);
  }

  @Override
  public Observable<Movie> getMovie(@NonNull Integer movieId) {
    return mApi.getMovie(movieId).flatMap(this::handleMovieResponse);
  }


  @Override
  public boolean hasMore() {
    return mPage < mTotalPages;
  }

  @Override
  public int nextPage() {
    return mNextPage;
  }
}

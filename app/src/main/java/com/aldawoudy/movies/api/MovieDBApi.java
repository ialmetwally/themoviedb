package com.aldawoudy.movies.api;

import com.aldawoudy.movies.api.model.MovieModel;
import com.aldawoudy.movies.api.model.MoviesResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ismail on 10/27/16.
 */
public interface MovieDBApi {
  /**
   * Popular Movies
   *
   * @param page page
   * @return the popular movies
   */
  @GET("movie/popular")
  Observable<Response<MoviesResponse>> getPopularMovies(@Query("page") Integer page);

  /**
   * Gets now playing movies.
   *
   * @param page the page
   * @return the now playing movies
   */
  @GET("movie/now_playing")
  Observable<Response<MoviesResponse>> getNowPlayingMovies(@Query("page") Integer page);

  /**
   * Gets top rated movies.
   *
   * @param page the page
   * @return the top rated movies
   */
  @GET("movie/top_rated")
  Observable<Response<MoviesResponse>> getTopRatedMovies(@Query("page") Integer page);

  /**
   * Gets upcoming movies.
   *
   * @param page the page
   * @return the upcoming movies
   */
  @GET("movie/upcoming")
  Observable<Response<MoviesResponse>> getUpcomingMovies(@Query("page") Integer page);

  /**
   * Gets movie.
   *
   * @param movieId the movie id
   * @return the movie
   */
  @GET("movie/{movie_id}?append_to_response=credits")
  Observable<Response<MovieModel>> getMovie(@Path("movie_id") Integer movieId);
}

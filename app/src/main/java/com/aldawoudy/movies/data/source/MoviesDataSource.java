package com.aldawoudy.movies.data.source;

import android.support.annotation.NonNull;
import com.aldawoudy.movies.data.Movie;
import java.util.List;
import rx.Observable;

/**
 * Created by Ismail on 10/27/16.
 */

public interface MoviesDataSource {

  Observable<List<Movie>> getPopularMovies(@NonNull Integer page);

  Observable<List<Movie>> getNowPlayingMovies(@NonNull Integer page);

  Observable<List<Movie>> getTopRatedMovies(@NonNull Integer page);

  Observable<List<Movie>> getUpcomingMovies(@NonNull Integer page);

  Observable<Movie> getMovie(@NonNull Integer movieId);

  boolean hasMore();

  int nextPage();

}

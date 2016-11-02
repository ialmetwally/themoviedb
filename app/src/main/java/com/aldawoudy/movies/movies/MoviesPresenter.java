package com.aldawoudy.movies.movies;

import android.support.annotation.NonNull;
import com.aldawoudy.movies.data.Movie;
import com.aldawoudy.movies.data.source.MoviesRepository;
import com.aldawoudy.movies.utils.schedulers.BaseSchedulerProvider;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscription;


/**
 * Created by Ismail on 10/27/16.
 */

public class MoviesPresenter implements MoviesContract.Presenter {


  private Subscription mSubscription;

  @NonNull
  private final MoviesRepository mMoviesRepository;

  @NonNull
  private final MoviesContract.View mMoviesView;

  @NonNull
  private final BaseSchedulerProvider mSchedulerProvider;

  @NonNull
  private MoviesFilterType mCurrentFiltering = MoviesFilterType.POPULAR;

  public MoviesPresenter(@NonNull MoviesRepository moviesRepository,
                         @NonNull MoviesContract.View moviesView,
                         @NonNull BaseSchedulerProvider schedulerProvider) {
    mMoviesRepository = moviesRepository;
    mMoviesView = moviesView;
    mSchedulerProvider = schedulerProvider;
    mMoviesView.setPresenter(this);
  }

  @Override
  public void loadMovies(Integer page) {
    mMoviesView.setLoadingIndicator(true);
    Observable<List<Movie>> observable;
    switch (mCurrentFiltering) {
      case UPCOMING:
        observable = mMoviesRepository.getUpcomingMovies(page);
        break;
      case TOP_RATED:
        observable = mMoviesRepository.getTopRatedMovies(page);
        break;
      case NOW_PLAYING:
        observable = mMoviesRepository.getNowPlayingMovies(page);
        break;
      default:
        observable = mMoviesRepository.getPopularMovies(page);
        break;
    }
    mSubscription = observable
        .subscribeOn(mSchedulerProvider.computation())
        .observeOn(mSchedulerProvider.ui())
        .subscribe(new Observer<List<Movie>>() {
          @Override
          public void onCompleted() {
            mMoviesView.setLoadingIndicator(false);
          }

          @Override
          public void onError(Throwable e) {
            mMoviesView.setLoadingIndicator(false);
            mMoviesView.showLoadingMoviesError();
          }

          @Override
          public void onNext(List<Movie> movies) {
            mMoviesView.showMovies(movies, page);
          }
        });
  }

  @Override
  public boolean hasMore() {
    return mMoviesRepository.hasMore();
  }

  @Override
  public void onScroll(int totalItemCount, int lastVisibleItem) {
    if (totalItemCount != 0 && lastVisibleItem == totalItemCount && hasMore()) {
      loadMovies(mMoviesRepository.nextPage());
    }
  }

  @Override
  public void subscribe() {
    loadMovies(1);
  }

  @Override
  public void unsubscribe() {
    if (mSubscription != null) {
      mSubscription.unsubscribe();
    }
  }

  public void setFiltering(MoviesFilterType currentFiltering) {
    mCurrentFiltering = currentFiltering;
  }

  @Override
  public void openMovieDetail(Movie movie) {
    mMoviesView.showMovieDetailsUi(movie.getId());
  }

  public MoviesFilterType getFiltering() {
    return mCurrentFiltering;
  }
}

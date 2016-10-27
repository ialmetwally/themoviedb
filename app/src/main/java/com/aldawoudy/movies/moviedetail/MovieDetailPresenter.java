package com.aldawoudy.movies.moviedetail;

import android.support.annotation.NonNull;
import com.aldawoudy.movies.data.Movie;
import com.aldawoudy.movies.data.source.MoviesRepository;
import com.aldawoudy.movies.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ismail on 10/27/16.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

  private final MoviesRepository mMoviesRepository;
  private final MovieDetailContract.View mMovieView;
  private final BaseSchedulerProvider mSchedulerProvider;
  private final CompositeSubscription mSubscriptions;
  private final Integer mMovieId;

  public MovieDetailPresenter(@NonNull Integer movieId,
      @NonNull MoviesRepository moviesRepository,
      @NonNull MovieDetailContract.View movieView,
      @NonNull BaseSchedulerProvider schedulerProvider) {
    mMovieId = movieId;
    mMoviesRepository = moviesRepository;
    mMovieView = movieView;
    mSchedulerProvider = schedulerProvider;
    mSubscriptions = new CompositeSubscription();
    mMovieView.setPresenter(this);
  }

  @Override
  public void loadMovie() {
    if (null == mMovieId || mMovieId == 0) {
      mMovieView.showMissingMovie();
      return;
    }
    mMovieView.setLoadingIndicator(true);
    mSubscriptions.clear();

    Subscription subscription = mMoviesRepository.getMovie(mMovieId)
        .subscribeOn(mSchedulerProvider.computation())
        .observeOn(mSchedulerProvider.ui())
        .subscribe(new Observer<Movie>() {
          @Override
          public void onCompleted() {
            mMovieView.setLoadingIndicator(false);
          }

          @Override
          public void onError(Throwable e) {
            mMovieView.showLoadingMovieError();
          }

          @Override
          public void onNext(Movie movie) {
            mMovieView.showMovie(movie);
          }
        });

    mSubscriptions.add(subscription);
  }

  @Override
  public void subscribe() {
    loadMovie();
  }

  @Override
  public void unsubscribe() {
    mSubscriptions.unsubscribe();
  }
}

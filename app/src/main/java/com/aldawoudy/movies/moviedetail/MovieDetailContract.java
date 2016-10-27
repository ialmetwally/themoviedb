package com.aldawoudy.movies.moviedetail;

import com.aldawoudy.movies.BasePresenter;
import com.aldawoudy.movies.BaseView;
import com.aldawoudy.movies.data.Movie;

/**
 * Created by Ismail on 10/27/16.
 */

public interface MovieDetailContract {

  interface View extends BaseView<Presenter> {

    void setLoadingIndicator(boolean active);

    void showMissingMovie();

    void showLoadingMovieError();

    void showMovie(Movie movie);
  }

  interface Presenter extends BasePresenter {

    void loadMovie();
  }
}

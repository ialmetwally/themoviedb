package com.aldawoudy.movies.movies;

import com.aldawoudy.movies.BasePresenter;
import com.aldawoudy.movies.BaseView;
import com.aldawoudy.movies.data.Movie;
import java.util.List;

/**
 * This specifies the contract between the view and presenter
 * Created by Ismail on 10/27/16.
 */
public interface MoviesContract {

  interface View extends BaseView<Presenter> {

    void showFilteringPopUpMenu();

    void setLoadingIndicator(boolean active);

    void showLoadingMoviesError();

    void showMovies(List<Movie> movies, Integer page);

    void showMovieDetailsUi(Integer id);
  }

  interface Presenter extends BasePresenter {

    void loadMovies(Integer page);

    boolean hasMore();

    void onScroll(int totalItemCount, int lastVisibleItem);

    void setFiltering(MoviesFilterType filterType);

    void openMovieDetail(Movie movie);
  }
}

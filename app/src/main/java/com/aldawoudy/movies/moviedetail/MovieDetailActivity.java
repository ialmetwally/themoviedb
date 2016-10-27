package com.aldawoudy.movies.moviedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import com.aldawoudy.movies.BaseActivity;
import com.aldawoudy.movies.R;

/**
 * Created by Ismail on 10/27/16.
 */

public class MovieDetailActivity extends BaseActivity {

  public static final String EXTRA_MOVIE_ID = "MOVIE_ID";

  private static final String MOVIE_DETAIL_FRAGMENT_TAG = "movie-detail-fragment";




  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movies);

    // Get the requested task id
    Integer movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);

    MovieDetailFragment fragment=
        (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    if (fragment == null) {
      // Create the fragment
      fragment = MovieDetailFragment.newInstance(movieId);
      replaceFragment(fragment, MOVIE_DETAIL_FRAGMENT_TAG, R.id.fragment_container, false);
    }

    // Create the presenter
    new MovieDetailPresenter(movieId, getComponent().provideMoviesRepository(),
        fragment,
        getComponent().provideSchedulerProvider());



    ButterKnife.bind(this);
    updateTitle(R.string.MOVIES);
  }

}

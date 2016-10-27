package com.aldawoudy.movies.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import com.aldawoudy.movies.BaseActivity;
import com.aldawoudy.movies.R;

/**
 * Created by Ismail on 10/27/16.
 */

public class MoviesActivity extends BaseActivity {

  private static final String MOVIES_FRAGMENT_TAG = "movies-fragment";

  private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

  private MoviesPresenter mMoviesPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movies);

    MoviesFragment fragment =
        (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    if (fragment == null) {
      // Create the fragment
      fragment = MoviesFragment.newInstance();
      replaceFragment(fragment, MOVIES_FRAGMENT_TAG, R.id.fragment_container, false);
    }

    // Create the presenter
    mMoviesPresenter = new MoviesPresenter(getComponent().provideMoviesRepository(),
        fragment,
        getComponent().provideSchedulerProvider());

    // Load previously saved state, if available.
    if (savedInstanceState != null) {
      MoviesFilterType currentFiltering =
          (MoviesFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
      mMoviesPresenter.setFiltering(currentFiltering);
    }

    ButterKnife.bind(this);
    updateTitle(R.string.MOVIES);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putSerializable(CURRENT_FILTERING_KEY, mMoviesPresenter.getFiltering());
    super.onSaveInstanceState(outState);
  }
}

package com.aldawoudy.movies.moviedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aldawoudy.movies.BaseFragment;
import com.aldawoudy.movies.R;
import com.aldawoudy.movies.data.Movie;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Created by Ismail on 10/27/16.
 */

public class MovieDetailFragment extends BaseFragment implements MovieDetailContract.View {

  private MovieDetailContract.Presenter mPresenter;

  public static final String ARGUMENT_MOVIE_ID = "MOVIE_ID";

  private static final String BASE_PATH = "https://image.tmdb.org/t/p/w500";

  @Bind(R.id.poster)
  ImageView mPoster;

  @Bind(R.id.title)
  TextView mTitle;

  @Bind(R.id.overview)
  TextView mOverView;

  @Bind(R.id.rating)
  TextView mRating;

  @Bind(R.id.cast)
  TextView mCast;

  @Bind(R.id.crew)
  TextView mCrew;

  @Inject
  Picasso mPicasso;

  public MovieDetailFragment() {
  }

  public static MovieDetailFragment newInstance(Integer movieId) {
    Bundle arguments = new Bundle();
    arguments.putInt(ARGUMENT_MOVIE_ID, movieId);
    MovieDetailFragment fragment = new MovieDetailFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

  @Override
  public void onResume() {
    super.onResume();
    mPresenter.subscribe();
  }

  @Override
  public void onPause() {
    super.onPause();
    mPresenter.unsubscribe();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
    getComponent().inject(this);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void setLoadingIndicator(boolean active) {

  }

  @Override
  public void showMissingMovie() {
    // TODO: 10/27/16
  }

  @Override
  public void showLoadingMovieError() {
    // TODO: 10/27/16
  }

  @Override
  public void showMovie(Movie movie) {
    mPicasso.load(BASE_PATH+movie.getBackdropPath()).into(mPoster);
    mTitle.setText(movie.getTitle());
    mOverView.setText(movie.getOverview());
    mRating.setText("Rating: "+String.valueOf(movie.getVoteAverage()));
  }

  @Override
  public void setPresenter(MovieDetailContract.Presenter presenter) {
    mPresenter = presenter;
  }
}

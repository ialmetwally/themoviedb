package com.aldawoudy.movies.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aldawoudy.movies.BaseFragment;
import com.aldawoudy.movies.R;
import com.aldawoudy.movies.data.Movie;
import com.aldawoudy.movies.moviedetail.MovieDetailActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Ismail on 10/27/16.
 */
public class MoviesFragment extends BaseFragment
    implements MoviesContract.View, MoviesAdapter.OnItemClickListener {

  LinearLayoutManager mLayoutManager;

  @Bind(R.id.recyclerView)
  RecyclerView recyclerView;

  @Bind(R.id.swipe_container)
  SwipeRefreshLayout mSwipeRefreshWidget;

  @Inject
  Picasso mPicasso;

  private MoviesContract.Presenter mPresenter;

  private MoviesAdapter mListAdapter;

  private boolean mLoading = false;

  private final RecyclerView.OnScrollListener mOnScrollListener =
      new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          if (dy > 0) {
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

            if (!mLoading) {
              if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                mLoading = true;
                mPresenter.onScroll(totalItemCount, firstVisibleItem + visibleItemCount);
              }
            }
          }
        }
      };

  public static MoviesFragment newInstance() {
    return new MoviesFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getComponent().inject(this);
    mListAdapter = new MoviesAdapter(new ArrayList<>(0), mPicasso, this);
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
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movies, container, false);
    ButterKnife.bind(this, view);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_filter:
        showFilteringPopUpMenu();
        break;
    }
    return true;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.movies_fragment_menu, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public void showFilteringPopUpMenu() {
    PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.menu_filter));
    popup.getMenuInflater().inflate(R.menu.movies_filter, popup.getMenu());

    popup.setOnMenuItemClickListener(item -> {
      switch (item.getItemId()) {
        case R.id.top_rated:
          mPresenter.setFiltering(MoviesFilterType.TOP_RATED);
          break;
        case R.id.now_playing:
          mPresenter.setFiltering(MoviesFilterType.NOW_PLAYING);
          break;
        case R.id.upcoming:
          mPresenter.setFiltering(MoviesFilterType.UPCOMING);
          break;
        default:
          mPresenter.setFiltering(MoviesFilterType.POPULAR);
          break;
      }
      mPresenter.loadMovies(1);
      return true;
    });

    popup.show();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(mListAdapter);
    recyclerView.addOnScrollListener(mOnScrollListener);
    mSwipeRefreshWidget.setColorSchemeColors(getColor(R.color.colorAccent),
        getColor(R.color.colorPrimary));
    mSwipeRefreshWidget.setOnRefreshListener(() -> mPresenter.loadMovies(1));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void setLoadingIndicator(boolean active) {
    mSwipeRefreshWidget.setRefreshing(active);
  }

  @Override
  public void showLoadingMoviesError() {
    Snackbar.make(getView(), R.string.ERROR_LOADING_MOVIES, Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void showMovies(List<Movie> movies, Integer page) {
    if (page == 1) {
      mListAdapter.replaceData(movies);
    } else {
      mListAdapter.addData(movies);
    }
    mLoading = false;
  }

  @Override
  public void showMovieDetailsUi(Integer id) {
    Intent intent = new Intent(getContext(), MovieDetailActivity.class);
    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, id);
    startActivity(intent);
  }

  @Override
  public void setPresenter(MoviesContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onItemClick(Movie movie) {
    mPresenter.openMovieDetail(movie);
  }

}

package com.aldawoudy.movies.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aldawoudy.movies.R;
import com.aldawoudy.movies.data.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Ismail on 10/27/16.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private final OnItemClickListener mItemClickListener;

  private final List<Movie> mMovies;

  private final Picasso mPicasso;

  private static final String BASE_PATH = "https://image.tmdb.org/t/p/w500";

  public MoviesAdapter(List<Movie> movies, Picasso picasso, OnItemClickListener itemClickListener) {
    mMovies = movies;
    mPicasso = picasso;
    mItemClickListener = itemClickListener;
  }


  public void replaceData(List<Movie> movies) {
    mMovies.clear();
    mMovies.addAll(movies);
    notifyDataSetChanged();
  }

  public void addData(List<Movie> movies) {
    mMovies.addAll(movies);
    notifyDataSetChanged();
  }

  @Override
  public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new MovieViewHolder(inflater.inflate(R.layout.card_movie, parent, false));
  }

  @Override
  public void onBindViewHolder(MovieViewHolder holder, int position) {
    Movie movie = mMovies.get(position);
    holder.title.setText(movie.getTitle());
    mPicasso.load(BASE_PATH+movie.getBackdropPath()).into(holder.poster);
  }

  @Override
  public int getItemCount() {
    return mMovies.size();
  }

  public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.poster)
    ImageView poster;

    public MovieViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (mItemClickListener != null) {
        mItemClickListener.onItemClick(mMovies.get(getLayoutPosition()));
      }
    }
  }

  public interface OnItemClickListener {

    void onItemClick(Movie movie);
  }
}

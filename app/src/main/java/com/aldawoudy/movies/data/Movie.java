package com.aldawoudy.movies.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.aldawoudy.movies.api.model.CastModel;
import com.aldawoudy.movies.api.model.CrewModel;
import com.aldawoudy.movies.api.model.GenreModel;
import com.aldawoudy.movies.api.model.MovieModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ismail on 10/27/16.
 */

public class Movie implements Parcelable {

  private String mPosterPath;

  private String mBackdropPath;

  private String mOverview;

  private String mReleaseDate;

  private List<String> mGenres;

  private List<CrewMember> mCrew;

  private List<CastMember> mCast;

  private String mBudget;

  private Integer mId;

  private String mTitle;

  private Double mVoteAverage;

  public Movie() {
  }

  public Movie(MovieModel model) {
    mPosterPath = model.getPosterPath();
    mBackdropPath = model.getPosterPath();
    mOverview = model.getOverview();
    mReleaseDate = model.getReleaseDate();
    extractGenres(model);
    extractCrewAndCast(model);
    mBudget = model.getBudget();
    mId = model.getId();
    mTitle = model.getTitle();
    mVoteAverage = model.getVoteAverage();
  }

  private void extractCrewAndCast(MovieModel model) {
    if (model.getCredits() != null) {
      if (model.getCredits().getCast() != null) {
        mCast = new ArrayList<>();
        for (CastModel castModel : model.getCredits().getCast()) {
          mCast.add(new CastMember(castModel));
        }
      }
      if (model.getCredits().getCrew() != null) {
        mCrew = new ArrayList<>();
        for (CrewModel crewModel : model.getCredits().getCrew()) {
          mCrew.add(new CrewMember(crewModel));
        }
      }
    }
  }

  private void extractGenres(MovieModel model) {
    if (model.getGenre() != null) {
      mGenres = new ArrayList<>();
      for (GenreModel genreModel : model.getGenre()) {
        mGenres.add(genreModel.getName());
      }
    }
  }

  public String getPosterPath() {
    return mPosterPath;
  }

  public void setPosterPath(String posterPath) {
    mPosterPath = posterPath;
  }

  public String getBackdropPath() {
    return mBackdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    mBackdropPath = backdropPath;
  }

  public String getOverview() {
    return mOverview;
  }

  public void setOverview(String overview) {
    mOverview = overview;
  }

  public String getReleaseDate() {
    return mReleaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    mReleaseDate = releaseDate;
  }

  public List<String> getGenres() {
    return mGenres;
  }

  public void setGenres(List<String> genres) {
    mGenres = genres;
  }

  public List<CrewMember> getCrew() {
    return mCrew;
  }

  public void setCrew(List<CrewMember> crew) {
    mCrew = crew;
  }

  public List<CastMember> getCast() {
    return mCast;
  }

  public void setCast(List<CastMember> cast) {
    mCast = cast;
  }

  public String getBudget() {
    return mBudget;
  }

  public void setBudget(String budget) {
    mBudget = budget;
  }

  public Integer getId() {
    return mId;
  }

  public void setId(Integer id) {
    mId = id;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public Double getVoteAverage() {
    return mVoteAverage;
  }

  public void setVoteAverage(Double voteAverage) {
    mVoteAverage = voteAverage;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mPosterPath);
    dest.writeString(mBackdropPath);
    dest.writeString(mOverview);
    dest.writeString(mReleaseDate);
    dest.writeStringList(mGenres);
    dest.writeTypedList(mCrew);
    dest.writeTypedList(mCast);
    dest.writeString(mBudget);
    dest.writeValue(mId);
    dest.writeString(mTitle);
    dest.writeValue(mVoteAverage);
  }

  protected Movie(Parcel in) {
    mPosterPath = in.readString();
    mBackdropPath = in.readString();
    mOverview = in.readString();
    mReleaseDate = in.readString();
    mGenres = in.createStringArrayList();
    mCrew = in.createTypedArrayList(CrewMember.CREATOR);
    mCast = in.createTypedArrayList(CastMember.CREATOR);
    mBudget = in.readString();
    mId = (Integer) in.readValue(Integer.class.getClassLoader());
    mTitle = in.readString();
    mVoteAverage = (Double) in.readValue(Double.class.getClassLoader());
  }

  public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
    @Override
    public Movie createFromParcel(Parcel source) {
      return new Movie(source);
    }

    @Override
    public Movie[] newArray(int size) {
      return new Movie[size];
    }
  };
}

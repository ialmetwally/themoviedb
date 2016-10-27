package com.aldawoudy.movies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


/**
 * Movie Model
 *
 * Created by Ismail on 10/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieModel {

  @JsonProperty("poster_path")
  private String mPosterPath;

  @JsonProperty("backdrop_path")
  private String mBackdropPath;

  @JsonProperty("overview")
  private String mOverview;

  @JsonProperty("release_date")
  private String mReleaseDate;

  @JsonProperty("id")
  private Integer mId;

  @JsonProperty("title")
  private String mTitle;

  @JsonProperty("vote_average")
  private Double mVoteAverage;

  @JsonProperty("budget")
  private String mBudget;

  @JsonProperty("genres")
  private List<GenreModel> mGenre;

  @JsonProperty("credits")
  private CreditsModel mCredits;

  public String getPosterPath() {
    return mPosterPath;
  }

  public String getBackdropPath() {
    return mBackdropPath;
  }

  public String getOverview() {
    return mOverview;
  }

  public String getReleaseDate() {
    return mReleaseDate;
  }

  public Integer getId() {
    return mId;
  }

  public String getTitle() {
    return mTitle;
  }

  public Double getVoteAverage() {
    return mVoteAverage;
  }

  public String getBudget() {
    return mBudget;
  }

  public List<GenreModel> getGenre() {
    return mGenre;
  }

  public CreditsModel getCredits() {
    return mCredits;
  }
}

package com.aldawoudy.movies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by Ismail on 10/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesResponse {

  @JsonProperty("results")
  private List<MovieModel> mResults;

  @JsonProperty("page")
  private Integer mPage;

  @JsonProperty("total_results")
  private Integer mTotalResults;

  @JsonProperty("total_pages")
  private Integer mTotalPages;

  public List<MovieModel> getResults() {
    return mResults;
  }

  public Integer getPage() {
    return mPage;
  }

  public Integer getTotalResults() {
    return mTotalResults;
  }

  public Integer getTotalPages() {
    return mTotalPages;
  }
}

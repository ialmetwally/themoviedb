package com.aldawoudy.movies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by Ismail on 10/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsModel {

  @JsonProperty("cast")
  private List<CastModel> mCast;

  @JsonProperty("crew")
  private List<CrewModel> mCrew;

  public List<CastModel> getCast() {
    return mCast;
  }

  public List<CrewModel> getCrew() {
    return mCrew;
  }
}

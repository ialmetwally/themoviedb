package com.aldawoudy.movies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ismail on 10/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreModel {

  @JsonProperty("id")
  private Integer mId;

  @JsonProperty("name")
  private String mName;

  public Integer getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }
}

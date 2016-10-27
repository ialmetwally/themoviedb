package com.aldawoudy.movies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cast Model
 * Created by Ismail on 10/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastModel {

  @JsonProperty("order")
  private int mOrder;

  @JsonProperty("name")
  private String mName;

  @JsonProperty("profile_path")
  private String mProfilePath;

  @JsonProperty("character")
  private String mCharacter;

  public int getOrder() {
    return mOrder;
  }

  public String getName() {
    return mName;
  }

  public String getProfilePath() {
    return mProfilePath;
  }

  public String getCharacter() {
    return mCharacter;
  }
}

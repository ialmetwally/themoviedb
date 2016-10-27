package com.aldawoudy.movies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ismail on 10/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewModel {

  @JsonProperty("department")
  private String mDepartment;

  @JsonProperty("name")
  private String mName;

  @JsonProperty("profile_path")
  private String mProfilePath;

  @JsonProperty("job")
  private String mJob;

  public String getDepartment() {
    return mDepartment;
  }

  public String getName() {
    return mName;
  }

  public String getProfilePath() {
    return mProfilePath;
  }

  public String getJob() {
    return mJob;
  }
}

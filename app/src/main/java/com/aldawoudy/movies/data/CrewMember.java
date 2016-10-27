package com.aldawoudy.movies.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.aldawoudy.movies.api.model.CrewModel;

/**
 * Created by Ismail on 10/27/16.
 */

public class CrewMember implements Parcelable {

  public static final Parcelable.Creator<CrewMember> CREATOR =
      new Parcelable.Creator<CrewMember>() {
        @Override
        public CrewMember createFromParcel(Parcel source) {
          return new CrewMember(source);
        }

        @Override
        public CrewMember[] newArray(int size) {
          return new CrewMember[size];
        }
      };
  private String mDepartment;
  private String mName;
  private String mProfilePath;
  private String mJob;

  public CrewMember(CrewModel model) {
    mDepartment = model.getDepartment();
    mName = model.getName();
    mProfilePath = model.getProfilePath();
    mJob = model.getJob();
  }

  public CrewMember() {
  }

  protected CrewMember(Parcel in) {
    mDepartment = in.readString();
    mName = in.readString();
    mProfilePath = in.readString();
    mJob = in.readString();
  }

  public String getDepartment() {
    return mDepartment;
  }

  public void setDepartment(String department) {
    mDepartment = department;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public String getProfilePath() {
    return mProfilePath;
  }

  public void setProfilePath(String profilePath) {
    mProfilePath = profilePath;
  }

  public String getJob() {
    return mJob;
  }

  public void setJob(String job) {
    mJob = job;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mDepartment);
    dest.writeString(mName);
    dest.writeString(mProfilePath);
    dest.writeString(mJob);
  }
}

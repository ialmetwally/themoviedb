package com.aldawoudy.movies.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.aldawoudy.movies.api.model.CastModel;

/**
 * Created by Ismail on 10/27/16.
 */

public class CastMember implements Parcelable {

  public static final Parcelable.Creator<CastMember> CREATOR =
      new Parcelable.Creator<CastMember>() {
        @Override
        public CastMember createFromParcel(Parcel source) {
          return new CastMember(source);
        }

        @Override
        public CastMember[] newArray(int size) {
          return new CastMember[size];
        }
      };
  private int mOrder;
  private String mName;
  private String mProfilePath;
  private String mCharacter;

  public CastMember(CastModel model) {
    mOrder = model.getOrder();
    mName = model.getName();
    mProfilePath = model.getProfilePath();
    mCharacter = model.getCharacter();
  }

  public CastMember() {
  }

  protected CastMember(Parcel in) {
    mOrder = in.readInt();
    mName = in.readString();
    mProfilePath = in.readString();
    mCharacter = in.readString();
  }

  public int getOrder() {
    return mOrder;
  }

  public void setOrder(int order) {
    mOrder = order;
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

  public String getCharacter() {
    return mCharacter;
  }

  public void setCharacter(String character) {
    mCharacter = character;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(mOrder);
    dest.writeString(mName);
    dest.writeString(mProfilePath);
    dest.writeString(mCharacter);
  }
}

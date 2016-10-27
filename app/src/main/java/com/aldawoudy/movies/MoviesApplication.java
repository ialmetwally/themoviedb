package com.aldawoudy.movies;

import android.app.Application;
import android.content.Context;
import com.aldawoudy.movies.di.MoviesComponent;
import com.aldawoudy.movies.di.MoviesModule;
import com.aldawoudy.movies.di.DaggerMoviesComponent;

/**
 * Created by Ismail on 10/27/16.
 */

public class MoviesApplication extends Application {

  private static MoviesApplication sInstance;

  private MoviesComponent mComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    sInstance = this;
  }

  /**
   * Gets context.
   *
   * @return the context
   */
  public static Context getContext() {
    return sInstance.getApplicationContext();
  }

  /**
   * Gets component.
   *
   * @return the component
   */
  public MoviesComponent getComponent() {
    if (mComponent == null) {
      String key = getString(R.string.api_key);
      String url = getString(R.string.base_url);
      String language = getString(R.string.language);
      MoviesModule module = new MoviesModule(getContext(), key, language, url);
      mComponent = DaggerMoviesComponent.builder().moviesModule(module).build();
    }
    return mComponent;
  }
}

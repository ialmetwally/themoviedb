package com.aldawoudy.movies.utils.schedulers;

import android.support.annotation.NonNull;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Provides different types of schedulers.
 * Created by Ismail on 10/27/16.
 */

@Singleton
public class SchedulerProvider implements BaseSchedulerProvider {

  @Override
  @NonNull
  public Scheduler computation() {
    return Schedulers.computation();
  }

  @Override
  @NonNull
  public Scheduler io() {
    return Schedulers.io();
  }

  @Override
  @NonNull
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}
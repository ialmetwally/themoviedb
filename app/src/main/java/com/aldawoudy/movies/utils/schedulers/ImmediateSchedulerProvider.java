package com.aldawoudy.movies.utils.schedulers;

import android.support.annotation.NonNull;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link BaseSchedulerProvider} making all {@link Scheduler}s immediate.
 * Created by Ismail on 10/27/16.
 */
public class ImmediateSchedulerProvider implements BaseSchedulerProvider {

  @NonNull
  @Override
  public Scheduler computation() {
    return Schedulers.immediate();
  }

  @NonNull
  @Override
  public Scheduler io() {
    return Schedulers.immediate();
  }

  @NonNull
  @Override
  public Scheduler ui() {
    return Schedulers.immediate();
  }
}

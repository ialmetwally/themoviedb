package com.aldawoudy.movies.utils.schedulers;

import android.support.annotation.NonNull;
import rx.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 *  * Created by Ismail on 10/27/16.
 */
public interface BaseSchedulerProvider {

  @NonNull
  Scheduler computation();

  @NonNull
  Scheduler io();

  @NonNull
  Scheduler ui();
}

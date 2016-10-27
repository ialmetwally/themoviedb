package com.aldawoudy.movies.di;

import android.content.Context;
import android.support.annotation.NonNull;
import com.aldawoudy.movies.api.ApiManager;
import com.aldawoudy.movies.api.MovieDBApi;
import com.aldawoudy.movies.data.source.MoviesDataSource;
import com.aldawoudy.movies.data.source.MoviesRepository;
import com.aldawoudy.movies.utils.schedulers.BaseSchedulerProvider;
import com.aldawoudy.movies.utils.schedulers.SchedulerProvider;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Ismail on 10/27/16.
 */
@Module
public class MoviesModule {

  @NonNull
  private final Context mContext;

  @NonNull
  private final String mApiKey;

  @NonNull
  private final String mBaseUrl;

  @NonNull
  private final String mLanguage;

  /**
   * Instantiates a new App module.
   *
   * @param context the context
   * @param apiKey the api key
   * @param language the language
   * @param baseUrl the base url
   */
  public MoviesModule(@NonNull Context context, @NonNull String apiKey, @NonNull String language,
      @NonNull String baseUrl) {
    mContext = context;
    mApiKey = apiKey;
    mLanguage = language;
    mBaseUrl = baseUrl;
  }

  /**
   * Provide api manager api manager.
   *
   * @return the api manager
   */
  @Provides
  @Singleton
  public ApiManager provideApiManager() {
    return new ApiManager(mApiKey, mLanguage);
  }

  /**
   * Provide movie db api movie db api.
   *
   * @param apiManager the api manager
   * @return the movie db api
   */
  @Provides
  @Singleton
  public MovieDBApi provideMovieDBApi(ApiManager apiManager) {
    return apiManager.create(mBaseUrl, MovieDBApi.class);
  }

  /**
   * Provide movies data source movies data source.
   *
   * @param movieDBApi the movie db api
   * @return the movies data source
   */
  @Provides
  @Singleton
  public MoviesRepository provideMoviesRepository(MovieDBApi movieDBApi) {
    return new MoviesRepository(movieDBApi);
  }

  @Provides
  @Singleton
  public BaseSchedulerProvider provideBaseSchedulerProvider() {
    return new SchedulerProvider();
  }

  /**
   * Provide picasso picasso.
   *
   * @return the picasso
   */
  @Provides
  @Singleton
  public Picasso providePicasso() {
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    httpClientBuilder.addInterceptor(new HttpLoggingInterceptor());
    Downloader downloader = new OkHttp3Downloader(httpClientBuilder.build());
    return new Picasso.Builder(mContext).downloader(downloader).build();
  }
}


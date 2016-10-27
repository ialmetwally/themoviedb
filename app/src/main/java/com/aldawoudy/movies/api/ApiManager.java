package com.aldawoudy.movies.api;

import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Ismail on 10/27/16.
 */

@Singleton
public class ApiManager {

  private final JacksonConverterFactory mConverterFactory;

  private final String mKey;
  private final String mLanguage;

  /**
   * Instantiates a new Api manager.
   *
   * @param key the key
   */
  public ApiManager(String key, String language) {
    mKey = key;
    mLanguage = language;
    mConverterFactory = JacksonConverterFactory.create();
  }

  /**
   * Build and return Retrofit instance
   *
   * @param baseUrl Request specific API Base URL
   * @param client {@link OkHttpClient} instance
   * @return {@link Retrofit} adapter instance
   */
  private Retrofit getRetrofit(String baseUrl, OkHttpClient client) {
    return new Retrofit.Builder()
        .addConverterFactory(mConverterFactory)
        .baseUrl(baseUrl)
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  /**
   * Creates Service Instance
   *
   * @param <T> the type parameter
   * @param baseURL request baseURL to override mBaseURL
   * @param service service class
   * @return the t
   */
  public <T> T create(String baseURL, Class<T> service) {
    Interceptor interceptor = new MovieDBInterceptor(mKey, mLanguage);
    return getRetrofit(baseURL, buildClient(interceptor)).create(service);
  }

  /**
   * Builds an OkHttpClient
   * @param interceptor custom interceptor
   * @return new {@link OkHttpClient} instance with logging enabled
   */
  private OkHttpClient buildClient(Interceptor interceptor) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    builder.addInterceptor(interceptor);
    builder.addInterceptor(loggingInterceptor);
    return builder.build();
  }
}

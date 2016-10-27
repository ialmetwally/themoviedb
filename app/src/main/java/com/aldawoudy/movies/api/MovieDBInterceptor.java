package com.aldawoudy.movies.api;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ismail on 10/27/16.
 */
class MovieDBInterceptor implements Interceptor {

  private final String mApiKey;
  private final String mLanguage;

  /**
   * Instantiates a new MovieModel db interceptor.
   *
   * @param apiKey the api key
   * @param language the language
   */
  MovieDBInterceptor(String apiKey, String language) {
    mApiKey = apiKey;
    mLanguage = language;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();
    HttpUrl originalHttpUrl = original.url();
    HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter("api_key", mApiKey).addQueryParameter("language", mLanguage).build();
    return chain.proceed(original.newBuilder().url(url).build());
  }
}

package com.yuanwei.interview.network;

import com.yuanwei.interview.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Clients {

  private static Retrofit sRetrofit;

  public static Retrofit getClient() {
    if (sRetrofit == null) {
      sRetrofit = new Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return sRetrofit;
  }
}

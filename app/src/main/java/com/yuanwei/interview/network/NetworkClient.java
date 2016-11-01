package com.yuanwei.interview.network;

import com.yuanwei.interview.models.GeneralModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;


public class NetworkClient {

  public static final String LATITUDE_KEY = "lat";
  public static final String LONGITUDE_KEY = "lng";

  private static GeneralModelApi client = Clients.getClient().create(GeneralModelApi.class);

  public static void getModels(String lat, String lng, final Callback<List<GeneralModel>> callback) {
    HashMap<String, String> params = new HashMap<>();
    params.put(LATITUDE_KEY, String.valueOf(lat));
    params.put(LONGITUDE_KEY, String.valueOf(lng));

    client.getGeneralModel(params).enqueue(callback);
  }
}

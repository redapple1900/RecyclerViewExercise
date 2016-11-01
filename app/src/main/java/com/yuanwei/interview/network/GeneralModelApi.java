package com.yuanwei.interview.network;

import com.yuanwei.interview.models.GeneralModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface GeneralModelApi {

  @GET("/v2/restaurant")
  Call<List<GeneralModel>> getGeneralModel(@QueryMap Map<String, String> params);
}

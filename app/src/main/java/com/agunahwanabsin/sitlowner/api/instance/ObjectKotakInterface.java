package com.agunahwanabsin.sitlowner.api.instance;

import com.agunahwanabsin.sitlowner.api.model.request.ObjectKotakRequest;
import com.agunahwanabsin.sitlowner.model.ObjectKotak;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ObjectKotakInterface {
    @Headers("Content-Type: application/json")
    @POST("object/list")
    Call<List<ObjectKotak>> getListObject(@Body ObjectKotakRequest body);
}

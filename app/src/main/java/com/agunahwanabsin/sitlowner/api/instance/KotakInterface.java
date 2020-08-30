package com.agunahwanabsin.sitlowner.api.instance;

import com.agunahwanabsin.sitlowner.api.model.request.Kotak;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface KotakInterface {
    @Headers("Content-Type: application/json")
    @POST("kotak/list")
    Call<List<com.agunahwanabsin.sitlowner.model.Kotak>> getListKotak(@Body Kotak body);
}

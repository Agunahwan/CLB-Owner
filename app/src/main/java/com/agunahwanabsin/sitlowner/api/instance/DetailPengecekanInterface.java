package com.agunahwanabsin.sitlowner.api.instance;

import com.agunahwanabsin.sitlowner.api.model.request.DetailPengecekanRequest;
import com.agunahwanabsin.sitlowner.model.DetailPengecekan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DetailPengecekanInterface {
    @Headers("Content-Type: application/json")
    @POST("detail_pengecekan/get")
    Call<List<DetailPengecekan>> getDetailPengecekan(@Body DetailPengecekanRequest body);
}

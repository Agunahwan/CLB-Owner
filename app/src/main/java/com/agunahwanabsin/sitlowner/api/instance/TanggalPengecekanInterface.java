package com.agunahwanabsin.sitlowner.api.instance;

import com.agunahwanabsin.sitlowner.api.model.request.TanggalPengecekanRequest;
import com.agunahwanabsin.sitlowner.model.TanggalPengecekan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TanggalPengecekanInterface {
    @Headers("Content-Type: application/json")
    @POST("jadwal/list")
    Call<List<TanggalPengecekan>> getListTanggalPengecekan(@Body TanggalPengecekanRequest body);
}

package com.agunahwanabsin.sitlowner.api.services;

import com.agunahwanabsin.sitlowner.api.helper.ApiClient;
import com.agunahwanabsin.sitlowner.api.instance.TanggalPengecekanInterface;

public class TanggalPengecekanApiService {
    public static TanggalPengecekanInterface getListTanggalPengecekan(){
        return ApiClient.getRetrofitInstance().create(TanggalPengecekanInterface.class);
    }
}

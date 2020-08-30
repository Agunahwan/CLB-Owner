package com.agunahwanabsin.sitlowner.api.services;

import com.agunahwanabsin.sitlowner.api.helper.ApiClient;
import com.agunahwanabsin.sitlowner.api.instance.DetailPengecekanInterface;

public class DetailPengecekanApiService {
    public static DetailPengecekanInterface getDetailPengecekan(){
        return ApiClient.getRetrofitInstance().create(DetailPengecekanInterface.class);
    }
}

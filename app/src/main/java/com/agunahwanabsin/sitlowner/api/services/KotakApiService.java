package com.agunahwanabsin.sitlowner.api.services;

import com.agunahwanabsin.sitlowner.api.helper.ApiClient;
import com.agunahwanabsin.sitlowner.api.instance.KotakInterface;

public class KotakApiService {
    public static KotakInterface getListKotak(){
        return ApiClient.getRetrofitInstance().create(KotakInterface.class);
    }
}

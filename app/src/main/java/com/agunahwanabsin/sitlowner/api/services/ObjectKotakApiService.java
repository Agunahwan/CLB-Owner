package com.agunahwanabsin.sitlowner.api.services;

import com.agunahwanabsin.sitlowner.api.helper.ApiClient;
import com.agunahwanabsin.sitlowner.api.instance.ObjectKotakInterface;

public class ObjectKotakApiService {
    public static ObjectKotakInterface getListObject(){
        return ApiClient.getRetrofitInstance().create(ObjectKotakInterface.class);
    }
}

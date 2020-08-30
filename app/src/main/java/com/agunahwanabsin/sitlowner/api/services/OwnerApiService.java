package com.agunahwanabsin.sitlowner.api.services;

import com.agunahwanabsin.sitlowner.api.helper.ApiClient;
import com.agunahwanabsin.sitlowner.api.instance.OwnerInterface;

public class OwnerApiService {
    public static OwnerInterface login(){
        return ApiClient.getRetrofitInstance().create(OwnerInterface.class);
    }

    public static OwnerInterface changePassword(){
        return ApiClient.getRetrofitInstance().create(OwnerInterface.class);
    }
}

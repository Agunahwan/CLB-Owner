package com.agunahwanabsin.sitlowner.api.instance;

import com.agunahwanabsin.sitlowner.api.model.request.ChangePasswordRequest;
import com.agunahwanabsin.sitlowner.api.model.request.ObjectKotakRequest;
import com.agunahwanabsin.sitlowner.api.model.response.ChangePasswordResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OwnerInterface {
    // Login
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("username") String Username,
                             @Field("password") String Password);

    // Change Password
    @Headers("Content-Type: application/json")
    @POST("change_password")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest body);
}

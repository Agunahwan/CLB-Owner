package com.agunahwanabsin.sitlowner.api.model.response;

import com.agunahwanabsin.sitlowner.model.Owner;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("IsSuccess")
    private boolean IsSuccess;
    @SerializedName("Message")
    private String Message;
    @SerializedName("Data")
    private Owner Data;

    public Login() {
    }

    public Login(boolean IsSuccess, String Message, Owner Data) {
        this.IsSuccess = IsSuccess;
        this.Message = Message;
        this.Data = Data;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Owner getData() {
        return Data;
    }

    public void setData(Owner data) {
        Data = data;
    }
}

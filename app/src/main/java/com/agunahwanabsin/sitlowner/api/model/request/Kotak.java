package com.agunahwanabsin.sitlowner.api.model.request;

import com.google.gson.annotations.SerializedName;

public class Kotak {
    @SerializedName("IdOwner")
    private int IdOwner;

    public Kotak(int idOwner) {
        IdOwner = idOwner;
    }

    public int getIdOwner() {
        return IdOwner;
    }

    public void setIdOwner(int idOwner) {
        IdOwner = idOwner;
    }
}

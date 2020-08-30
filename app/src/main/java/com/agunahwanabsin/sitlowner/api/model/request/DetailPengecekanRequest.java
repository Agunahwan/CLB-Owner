package com.agunahwanabsin.sitlowner.api.model.request;

import com.google.gson.annotations.SerializedName;

public class DetailPengecekanRequest {
    @SerializedName("IdHasilPengecekan")
    private int IdHasilPengecekan;
    @SerializedName("IdObject")
    private int IdObject;

    public DetailPengecekanRequest(int idHasilPengecekan, int idObject) {
        IdHasilPengecekan = idHasilPengecekan;
        IdObject = idObject;
    }

    public int getIdHasilPengecekan() {
        return IdHasilPengecekan;
    }

    public void setIdHasilPengecekan(int idHasilPengecekan) {
        IdHasilPengecekan = idHasilPengecekan;
    }

    public int getIdObject() {
        return IdObject;
    }

    public void setIdObject(int idObject) {
        IdObject = idObject;
    }
}

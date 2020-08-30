package com.agunahwanabsin.sitlowner.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {
    @SerializedName("IdOwner")
    private int IdOwner;
    @SerializedName("OldPassword")
    private String OldPassword;
    @SerializedName("NewPassword")
    private String NewPassword;
    @SerializedName("ConfirmPassword")
    private String ConfirmPassword;

    public ChangePasswordRequest(int idOwner, String oldPassword, String newPassword, String confirmPassword) {
        IdOwner = idOwner;
        OldPassword = oldPassword;
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
    }

    public int getIdOwner() {
        return IdOwner;
    }

    public void setIdOwner(int idOwner) {
        IdOwner = idOwner;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}

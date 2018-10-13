package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserUpdatePasswordApi {
    @SerializedName("STATUS")
    @Expose
    String status;
    @SerializedName("message")
    @Expose
    String message;

    public UserUpdatePasswordApi() {
    }

    public UserUpdatePasswordApi(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

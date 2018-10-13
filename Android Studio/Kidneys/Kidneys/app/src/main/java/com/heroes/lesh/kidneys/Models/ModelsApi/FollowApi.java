package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowApi {
    @SerializedName("idfollow")
    @Expose
    private int idFollow;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("idhealthrange")
    @Expose
    private int idhealthrange;

    public FollowApi() {

    }

    public FollowApi(int idFollow, String date, int idhealthrange) {
        this.idFollow = idFollow;
        this.date = date;
        this.idhealthrange = idhealthrange;
    }

    public int getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(int idFollow) {
        this.idFollow = idFollow;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdhealthrange() {
        return idhealthrange;
    }

    public void setIdhealthrange(int idhealthrange) {
        this.idhealthrange = idhealthrange;
    }
}

package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FollowsApi {
    @SerializedName("follows")
    @Expose
    private ArrayList<FollowApi> followApis;

    public FollowsApi() {

    }

    public FollowsApi(ArrayList<FollowApi> followApis) {
        this.followApis = followApis;
    }

    public ArrayList<FollowApi> getFollowApis() {
        return followApis;
    }

    public void setFollowApis(ArrayList<FollowApi> followApis) {
        this.followApis = followApis;
    }
}

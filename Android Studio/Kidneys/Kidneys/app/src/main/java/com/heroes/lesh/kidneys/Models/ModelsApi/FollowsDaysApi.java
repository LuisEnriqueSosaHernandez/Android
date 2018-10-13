package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FollowsDaysApi {
    @SerializedName("followsday")
    @Expose
    ArrayList<FollowsDayApi> followsDayApis;
    public FollowsDaysApi(){

    }

    public FollowsDaysApi(ArrayList<FollowsDayApi> followsDayApis) {
        this.followsDayApis = followsDayApis;
    }

    public ArrayList<FollowsDayApi> getFollowsDayApis() {
        return followsDayApis;
    }

    public void setFollowsDayApis(ArrayList<FollowsDayApi> followsDayApis) {
        this.followsDayApis = followsDayApis;
    }
}

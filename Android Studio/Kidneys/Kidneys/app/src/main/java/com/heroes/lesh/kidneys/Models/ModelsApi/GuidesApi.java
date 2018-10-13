package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GuidesApi {
    @SerializedName("guides")
    @Expose
    private ArrayList<GuideApi> guideApis;

    public GuidesApi() {

    }

    public GuidesApi(ArrayList<GuideApi> guideApis) {
        this.guideApis = guideApis;
    }

    public ArrayList<GuideApi> getGuideApis() {
        return guideApis;
    }

    public void setGuideApis(ArrayList<GuideApi> guideApis) {
        this.guideApis = guideApis;
    }
}

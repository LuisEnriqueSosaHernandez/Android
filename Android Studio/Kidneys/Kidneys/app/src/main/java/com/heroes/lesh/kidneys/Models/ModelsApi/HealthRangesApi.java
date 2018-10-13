package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HealthRangesApi {
    @SerializedName("healthranges")
    @Expose
    private ArrayList<HealthRangeApi> healthRangeApis;

    public HealthRangesApi() {

    }

    public HealthRangesApi(ArrayList<HealthRangeApi> healthRangeApis) {
        this.healthRangeApis = healthRangeApis;
    }

    public ArrayList<HealthRangeApi> getHealthRangeApis() {
        return healthRangeApis;
    }

    public void setHealthRangeApis(ArrayList<HealthRangeApi> healthRangeApis) {
        this.healthRangeApis = healthRangeApis;
    }
}

package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class LocationsApi {
    @SerializedName("locations")
    @Expose
    ArrayList<LocationApi> locationApis;

    public LocationsApi() {

    }

    public LocationsApi(ArrayList<LocationApi> locationApis) {
        this.locationApis = locationApis;
    }

    public ArrayList<LocationApi> getLocationApis() {
        return locationApis;
    }

    public void setLocationApis(ArrayList<LocationApi> locationApis) {
        this.locationApis = locationApis;
    }
}

package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationApi {
    @SerializedName("idlocation")
    @Expose
    private int idLocation;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("longitude")
    @Expose
    double longitude;
    @SerializedName("latitude")
    @Expose
    double latitude;
    @SerializedName("idcategory")
    @Expose
    int idcategory;


    public LocationApi() {

    }

    public LocationApi(int idLocation, String name, double longitude, double latitude, int idcategory) {
        this.idLocation = idLocation;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.idcategory = idcategory;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }
}

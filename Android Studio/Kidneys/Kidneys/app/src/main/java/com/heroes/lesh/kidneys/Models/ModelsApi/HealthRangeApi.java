package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HealthRangeApi {
    @SerializedName("idhealthrange")
    @Expose
    int idHealthRange;
    @SerializedName("name")
    @Expose
    String name;

    public HealthRangeApi() {

    }

    public HealthRangeApi(int idHealthRange, String name) {
        this.idHealthRange = idHealthRange;
        this.name = name;
    }

    public int getIdHealthRange() {
        return idHealthRange;
    }

    public void setIdHealthRange(int idHealthRange) {
        this.idHealthRange = idHealthRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

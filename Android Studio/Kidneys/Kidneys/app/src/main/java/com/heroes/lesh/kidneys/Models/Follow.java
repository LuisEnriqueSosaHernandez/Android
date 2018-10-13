package com.heroes.lesh.kidneys.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Follow {
    private int idFollow;
    private String date;
    private String healthRangeName;

    public Follow() {
    }

    public Follow(int idFollow, String date, String healthRangeName) {
        this.idFollow = idFollow;
        this.date = date;
        this.healthRangeName = healthRangeName;
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

    public String getHealthRangeName() {
        return healthRangeName;
    }

    public void setHealthRangeName(String healthRangeName) {
        this.healthRangeName = healthRangeName;
    }
}

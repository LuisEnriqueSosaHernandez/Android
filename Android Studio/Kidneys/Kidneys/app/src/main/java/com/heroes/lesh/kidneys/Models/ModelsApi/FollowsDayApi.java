package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.heroes.lesh.kidneys.Models.Follow;

public class FollowsDayApi {
    @SerializedName("idfollowday")
    @Expose
    private int idFollowDay;
    @SerializedName("typeofsolution")
    @Expose
    private double typeOfSolution;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("drainage")
    @Expose
    private double drainage;
    @SerializedName("uf")
    @Expose
    private double uf;
    @SerializedName("ingestedliquid")
    @Expose
    private double ingestedLiquid;
    @SerializedName("idfollow")
    @Expose
    private int idFollow;
    @SerializedName("email")
    @Expose
    private String email;
    public FollowsDayApi(){

    }

    public FollowsDayApi(int idFollowDay, double typeOfSolution, String start, String end, double drainage, double uf, double ingestedLiquid, int idFollow, String email) {
        this.idFollowDay = idFollowDay;
        this.typeOfSolution = typeOfSolution;
        this.start = start;
        this.end = end;
        this.drainage = drainage;
        this.uf = uf;
        this.ingestedLiquid = ingestedLiquid;
        this.idFollow = idFollow;
        this.email = email;
    }

    public int getIdFollowDay() {
        return idFollowDay;
    }

    public void setIdFollowDay(int idFollowDay) {
        this.idFollowDay = idFollowDay;
    }

    public double getTypeOfSolution() {
        return typeOfSolution;
    }

    public void setTypeOfSolution(double typeOfSolution) {
        this.typeOfSolution = typeOfSolution;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getDrainage() {
        return drainage;
    }

    public void setDrainage(double drainage) {
        this.drainage = drainage;
    }

    public double getUf() {
        return uf;
    }

    public void setUf(double uf) {
        this.uf = uf;
    }

    public double getIngestedLiquid() {
        return ingestedLiquid;
    }

    public void setIngestedLiquid(double ingestedLiquid) {
        this.ingestedLiquid = ingestedLiquid;
    }

    public int getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(int idFollow) {
        this.idFollow = idFollow;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

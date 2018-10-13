package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodsApi {
    @SerializedName("foods")
    @Expose
    private ArrayList<FoodApi> foodApis;

    public FoodsApi() {

    }

    public FoodsApi(ArrayList<FoodApi> foodApis) {
        this.foodApis = foodApis;
    }

    public ArrayList<FoodApi> getFoodApis() {
        return foodApis;
    }

    public void setFoodApis(ArrayList<FoodApi> foodApis) {
        this.foodApis = foodApis;
    }
}

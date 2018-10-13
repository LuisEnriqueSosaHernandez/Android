package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodApi {
    @SerializedName("idfood")
    @Expose
    private int idFood;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("idcategory")
    @Expose
    private int idCategory;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("idhealthrange")
    @Expose
    private int idHealthRange;
    @SerializedName("portion")
    @Expose
    String portion;
    @SerializedName("sodium")
    @Expose
    String sodium;
    @SerializedName("potassium")
    @Expose
    String potassium;
    @SerializedName("phosphor")
    @Expose
    String phosphor;

    public FoodApi() {
    }

    public FoodApi(int idFood, String name, String description, int idCategory, String image, int idHealthRange, String portion, String sodium, String potassium, String phosphor) {
        this.idFood = idFood;
        this.name = name;
        this.description = description;
        this.idCategory = idCategory;
        this.image = image;
        this.idHealthRange = idHealthRange;
        this.portion = portion;
        this.sodium = sodium;
        this.potassium = potassium;
        this.phosphor = phosphor;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdHealthRange() {
        return idHealthRange;
    }

    public void setIdHealthRange(int idHealthRange) {
        this.idHealthRange = idHealthRange;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getPotassium() {
        return potassium;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    public String getPhosphor() {
        return phosphor;
    }

    public void setPhosphor(String phosphor) {
        this.phosphor = phosphor;
    }
}

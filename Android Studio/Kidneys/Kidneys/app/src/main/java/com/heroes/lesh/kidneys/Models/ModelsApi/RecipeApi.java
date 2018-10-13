package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeApi {
    @SerializedName("idrecipe")
    @Expose
    private int idRecipe;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("prepare")
    @Expose
    private String prepare;
    @SerializedName("ingredients")
    @Expose
    private String ingredients;
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

    public RecipeApi() {

    }

    public RecipeApi(int idRecipe, String title, String description, String prepare, String ingredients, String image, int idHealthRange, String portion, String sodium, String potassium, String phosphor) {
        this.idRecipe = idRecipe;
        this.title = title;
        this.description = description;
        this.prepare = prepare;
        this.ingredients = ingredients;
        this.image = image;
        this.idHealthRange = idHealthRange;
        this.portion = portion;
        this.sodium = sodium;
        this.potassium = potassium;
        this.phosphor = phosphor;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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

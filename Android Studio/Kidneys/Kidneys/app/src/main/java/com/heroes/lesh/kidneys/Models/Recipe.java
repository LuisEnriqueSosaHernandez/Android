package com.heroes.lesh.kidneys.Models;

public class Recipe {
    //variables globales
    private int idRecipe;
    private String title;
    private String description;
    private String ingredients;
    private String prepare;
    private String image;
    private String healthRangeName;
    private String portion;
    private String sodium;
    private String potassium;
    private String phosphor;

    public Recipe() {

    }

    public Recipe(int idRecipe, String title, String description, String ingredients, String prepare, String image, String healthRangeName, String portion, String sodium, String potassium, String phosphor) {
        this.idRecipe = idRecipe;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.prepare = prepare;
        this.image = image;
        this.healthRangeName = healthRangeName;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHealthRangeName() {
        return healthRangeName;
    }

    public void setHealthRangeName(String healthRangeName) {
        this.healthRangeName = healthRangeName;
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

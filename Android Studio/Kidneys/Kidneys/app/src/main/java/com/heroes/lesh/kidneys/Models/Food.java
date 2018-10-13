package com.heroes.lesh.kidneys.Models;

public class Food {
    //variables globales
    private int idFood;
    private String name;
    private String description;
    private String category;
    private String image;
    private String healthRangeName;
    private String portion;
    private String sodium;
    private String potassium;
    private String phosphor;

    public Food() {

    }

    public Food(int idFood, String name, String description, String category, String image, String healthRangeName, String portion, String sodium, String potassium, String phosphor) {
        this.idFood = idFood;
        this.name = name;
        this.description = description;
        this.category = category;
        this.image = image;
        this.healthRangeName = healthRangeName;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

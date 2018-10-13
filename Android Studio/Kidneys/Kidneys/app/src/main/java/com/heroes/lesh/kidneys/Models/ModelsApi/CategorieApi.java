package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CategorieApi {
    @SerializedName("idcategory")
    @Expose
    private int idCategory;
    @SerializedName("name")
    @Expose
    private String name;

    public CategorieApi() {

    }

    public CategorieApi(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

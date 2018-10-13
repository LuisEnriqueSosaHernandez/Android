package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipesApi {
    @SerializedName("recipes")
    @Expose
    private ArrayList<RecipeApi> recipeApis;

    public RecipesApi() {

    }

    public RecipesApi(ArrayList<RecipeApi> recipeApis) {
        this.recipeApis = recipeApis;
    }

    public ArrayList<RecipeApi> getRecipeApis() {
        return recipeApis;
    }

    public void setRecipeApis(ArrayList<RecipeApi> recipeApis) {
        this.recipeApis = recipeApis;
    }
}

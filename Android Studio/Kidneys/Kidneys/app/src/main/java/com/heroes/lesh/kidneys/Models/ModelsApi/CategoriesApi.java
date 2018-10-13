package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesApi {
    //El serialized es para convertir del nombre que viene de la api, a el nombre que ponemos aqui, cambia
    //categories a categoriesApi que es lo que queremos recibir
    @SerializedName("categories")
    @Expose
    private ArrayList<CategorieApi> categoriesApi;

    public CategoriesApi() {

    }

    public CategoriesApi(ArrayList<CategorieApi> categoriesApi) {
        this.categoriesApi = categoriesApi;
    }

    public ArrayList<CategorieApi> getCategoriesApi() {
        return categoriesApi;
    }

    public void setCategoriesApi(ArrayList<CategorieApi> categoriesApi) {
        this.categoriesApi = categoriesApi;
    }
}

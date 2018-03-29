package com.example.lesh.zonaverde.FragmentsRegistro.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by LESH on 29/03/2018.
 */
//Clase que sirve como objeto para contener los municipios
public class Municipios {
    private List<Municipio> municipios;
    public Municipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    public List<Municipio> getMunicipios() {
        return municipios;
    }
    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    //Metodo para parsear los resultados a json
    public static Municipio parseJSON(String response){
        Gson gson=new GsonBuilder().create();
        Municipio municipio=gson.fromJson(response,Municipio.class);
        return municipio;
    }
}

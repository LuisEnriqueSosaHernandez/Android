package com.example.lesh.zonaverde.FragmentsRegistro.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by LESH on 29/03/2018.
 */
//Clase que sirve como objeto para contener todos los estados
public class Suelos {
    private List<Suelo> suelos;
    public Suelos(List<Suelo> suelos) {
        this.suelos = suelos;
    }
    public List<Suelo> getSuelos() {
        return suelos;
    }
    public void setSuelos(List<Suelo> suelos) {
        this.suelos = suelos;
    }
    //Metodo para parsear los resultados a json
    public static Suelo parseJSON(String response){
        Gson gson=new GsonBuilder().create();
        Suelo suelo=gson.fromJson(response,Suelo.class);
        return suelo;
    }
}

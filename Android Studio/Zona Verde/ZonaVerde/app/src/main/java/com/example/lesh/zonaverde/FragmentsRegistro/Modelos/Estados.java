package com.example.lesh.zonaverde.FragmentsRegistro.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by LESH on 27/03/2018.
 */
//Clase que sirve como contenedor de todos los estados
public class Estados {
    private List<Estado> estados;
    public Estados(){

    }

    public Estados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
//Metodo para parsear los resultados a json
    public static Estado parseJSON(String response){
        Gson gson=new GsonBuilder().create();
        Estado estado=gson.fromJson(response,Estado.class);
        return estado;
    }
}

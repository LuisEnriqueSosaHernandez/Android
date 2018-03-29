package com.example.lesh.zonaverde.FragmentsRegistro.Modelos;

/**
 * Created by LESH on 29/03/2018.
 */
//Clase que sirve como objeto que contiene a un tipo de suelo
public class Suelo {
    private String Nombre;
    public Suelo(){

    }
    public Suelo(String Nombre){
        this.Nombre=Nombre;
    }

    public String getSuelo() {
        return Nombre;
    }

    public void setSuelo(String Nombre) {
        this.Nombre = Nombre;
    }
}

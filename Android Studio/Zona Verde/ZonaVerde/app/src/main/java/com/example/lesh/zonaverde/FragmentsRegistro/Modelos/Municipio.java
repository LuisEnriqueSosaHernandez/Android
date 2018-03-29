package com.example.lesh.zonaverde.FragmentsRegistro.Modelos;

/**
 * Created by LESH on 29/03/2018.
 */
//Clase que sirve como objeto para contener un municipio
public class Municipio {
    private String Nombre;
    public Municipio(){

    }
    public Municipio(String Nombre){
        this.Nombre=Nombre;
    }

    public String getMunicipio() {
        return Nombre;
    }

    public void setMunicipio(String Nombre) {
        this.Nombre = Nombre;
    }
}

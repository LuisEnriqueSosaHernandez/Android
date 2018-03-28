package com.example.lesh.zonaverde.FragmentsRegistro.Modelos;

/**
 * Created by LESH on 27/03/2018.
 */
//Clase que sirve como objeto para contener el estado
public class Estado {
    private String Nombre;

    public Estado(){

    }
    public Estado( String Nombre) {

        this.Nombre = Nombre;
    }

    public String getEstado() {
        return Nombre;
    }

    public void setEstado(String Nombre) {
        this.Nombre = Nombre;
    }
}

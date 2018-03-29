package com.example.lesh.zonaverde.Interfaces.Api;

import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Estados;
import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Municipios;
import com.example.lesh.zonaverde.FragmentsRegistro.Modelos.Suelos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LESH on 27/03/2018.
 */
//Interfaz para las peticiones HTTP
public interface ZonaVerdeService {
    //Metodo que devuelve los estados
    @GET("index.php/{metodo}")
    Call<Estados> getEstados(@Path("metodo") String metodo);
    //Metodo que devuelve los municipios del estado seleccionado
    @GET("index.php/{metodo}/{idEstado}")
    Call<Municipios> getMunicipios(@Path("metodo") String metodo,@Path("idEstado") int idEstado);
    //Metodo que devuelve los tipos de suelos
    @GET("index.php/{metodo}")
    Call<Suelos> getSuelos(@Path("metodo") String metodo);
}

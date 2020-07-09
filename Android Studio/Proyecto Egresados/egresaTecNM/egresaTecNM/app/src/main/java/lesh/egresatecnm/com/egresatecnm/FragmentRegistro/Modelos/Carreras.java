package lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by LESH on 21/10/2017.
 */

public class Carreras {
    private List<Carrera> carreras;
    public Carreras(){

    }

    public Carreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
    public static Carrera parseJSON(String response){
        Gson gson=new GsonBuilder().create();
        Carrera carrera=gson.fromJson(response,Carrera.class);
        return carrera;
    }
}

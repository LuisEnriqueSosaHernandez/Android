package lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by LESH on 21/10/2017.
 */

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

    public static Estado parseJSON(String response){
        Gson gson=new GsonBuilder().create();
        Estado estado=gson.fromJson(response,Estado.class);
        return estado;
    }
}

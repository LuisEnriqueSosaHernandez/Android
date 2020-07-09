package lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by LESH on 21/10/2017.
 */

public class Tecnologicos {
    private List<Tecnologico> tecnologicos;
    public Tecnologicos(){

    }

    public Tecnologicos(List<Tecnologico> tecnologicos) {
        this.tecnologicos = tecnologicos;
    }

    public List<Tecnologico> getTecnologicos() {
        return tecnologicos;
    }

    public void setTecnologicos(List<Tecnologico> tecnologicos) {
        this.tecnologicos = tecnologicos;
    }
    public static Tecnologico parseJSON(String response){
        Gson gson=new GsonBuilder().create();
        Tecnologico tecnologico=gson.fromJson(response,Tecnologico.class);
        return tecnologico;
    }
}

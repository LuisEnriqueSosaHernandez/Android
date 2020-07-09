package lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos;

/**
 * Created by LESH on 21/10/2017.
 */

public class Tecnologico {
    private String idTecnologico;
    private String Tecnologico;
    private Tecnologico(){

    }

    public Tecnologico(String idTecnologico, String tecnologico) {
        this.idTecnologico = idTecnologico;
        Tecnologico = tecnologico;
    }

    public String getIdTecnologico() {
        return idTecnologico;
    }

    public void setIdTecnologico(String idTecnologico) {
        this.idTecnologico = idTecnologico;
    }

    public String getTecnologico() {
        return Tecnologico;
    }

    public void setTecnologico(String tecnologico) {
        Tecnologico = tecnologico;
    }
}

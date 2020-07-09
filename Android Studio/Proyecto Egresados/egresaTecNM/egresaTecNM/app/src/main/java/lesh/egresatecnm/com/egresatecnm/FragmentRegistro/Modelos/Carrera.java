package lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos;

/**
 * Created by LESH on 21/10/2017.
 */

public class Carrera {
    private String idCarrera;
    private String nCarrera;
    public Carrera(){

    }

    public Carrera(String idCarrera, String nCarrera) {
        this.idCarrera = idCarrera;
        this.nCarrera = nCarrera;
    }

    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNcarrera() {
        return nCarrera;
    }

    public void setNcarrera(String ncarrera) {
        this.nCarrera = ncarrera;
    }
}

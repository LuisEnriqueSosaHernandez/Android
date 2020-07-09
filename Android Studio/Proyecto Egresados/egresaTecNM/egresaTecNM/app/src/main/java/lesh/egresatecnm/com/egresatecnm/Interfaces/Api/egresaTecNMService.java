package lesh.egresatecnm.com.egresatecnm.Interfaces.Api;

import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos.Carreras;
import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos.Estados;
import lesh.egresatecnm.com.egresatecnm.FragmentRegistro.Modelos.Tecnologicos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LESH on 21/10/2017.
 */

public interface egresaTecNMService {
    //http://www.prograweb.com.mx/egresaTecNM/ws/index?metodo=obtenerEstados
    @GET("index")
    Call<Estados> getEstados(@Query("metodo") String metodo);
    @GET("index")
    Call<Tecnologicos> getTecnologicos(@Query("metodo") String metodo, @Query("idEstado") String idEstado);
    @GET("index")
    Call<Carreras> getCarreras(@Query("metodo") String metodo, @Query("idEstado") String idEstado,@Query("idTecnologico") String idTecnologico);

}

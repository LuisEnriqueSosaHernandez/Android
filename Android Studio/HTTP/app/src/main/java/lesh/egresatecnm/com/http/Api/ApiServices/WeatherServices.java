package lesh.egresatecnm.com.http.Api.ApiServices;

import lesh.egresatecnm.com.http.Models.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LESH on 21/10/2017.
 */

public interface WeatherServices {
    //weather?q=Seville,es&appid=d69c19dbe649e8d3982d237ea9b8723c
    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid")String key);
    @GET("weather")
    Call<City> getCityCelsius(@Query("q") String city, @Query("appid")String key,@Query("units")String value);
}


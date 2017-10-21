package lesh.egresatecnm.com.http.Api;

import com.google.gson.GsonBuilder;

import lesh.egresatecnm.com.http.Api.Deserializable.mideserializable;
import lesh.egresatecnm.com.http.Models.City;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LESH on 21/10/2017.
 */

public class API {
    public static final String BASE_URL="http://api.openweathermap.org/data/2.5/";
    public static final String APPKEY="d69c19dbe649e8d3982d237ea9b8723c";
    private static Retrofit retrofit=null;

    public static Retrofit getApi(){
        if(retrofit==null){
            GsonBuilder builder=new GsonBuilder();
            builder.registerTypeAdapter(City.class,new mideserializable());
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }

}

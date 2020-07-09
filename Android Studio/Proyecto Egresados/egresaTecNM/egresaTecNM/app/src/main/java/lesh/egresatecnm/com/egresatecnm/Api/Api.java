package lesh.egresatecnm.com.egresatecnm.Api;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LESH on 21/10/2017.
 */

public class Api {
    public static final String BASE_URL="http://www.prograweb.com.mx/egresaTecNM/ws/";
    private static Retrofit retrofit=null;

    public static Retrofit getApi(){
        if(retrofit==null){
            GsonBuilder builder=new GsonBuilder();
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }
}

package com.example.lesh.zonaverde.Api;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LESH on 27/03/2018.
 */

public class Api {
    //Variables globales
    public static final String BASE_URL="http://192.168.0.4:8000/ZonaVerde/";
    private static Retrofit retrofit=null;
//Metodo para crear el retrofit
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

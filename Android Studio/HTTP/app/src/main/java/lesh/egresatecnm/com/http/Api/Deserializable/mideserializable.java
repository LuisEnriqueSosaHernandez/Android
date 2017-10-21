package lesh.egresatecnm.com.http.Api.Deserializable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import lesh.egresatecnm.com.http.Models.City;

/**
 * Created by LESH on 21/10/2017.
 */

public class mideserializable implements JsonDeserializer<City> {
    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
       int id=json.getAsJsonObject().get("id").getAsInt();
        String name=json.getAsJsonObject().get("name").getAsString();
        String country=json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString();
        City city=new City(id,name,country);
        return city;
    }
}

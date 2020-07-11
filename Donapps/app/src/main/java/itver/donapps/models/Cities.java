package itver.donapps.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Cities extends ArrayList<City> {

    private static Cities cities = new Cities();

    private String[] citiesModel ={
            "AGUASCALIENTES", "BAJA CALIFORNIA",
            "BAJA CALIFORNIA SUR", "CAMPECHE",
            "COAHUILA", "COLIMA", "CHIHUAHUA",
            "CHIAPAS", "CIUDAD DE MÉXICO",
            "DURANGO", "GUERRERO", "GUANAJUATO",
            "HIDALGO", "JALILSCO", "MICHOACAN",
            "ESTADO DE MÉXICO", "MORELOS", "NAYARIT",
            "NUEVO LEÓN", "OAXACA", "PUEBLA",
            "QUINTANA ROO", "QUERETARO", "SINALOA",
            "SAN LUIS POTOSI", "SONORA", "TABASCO",
            "TLAXCALA","TAMAULIPAS","VERACRUZ","YUCATAN",
            "ZACATECAS"
    };

    private Cities(){
        for (int i = 0; i < citiesModel.length; i++ ){
            add(new City(i+1, citiesModel[i]));
        }
    }

    public static Cities getCities(){
        return cities;
    }

    public static int getCityID (String city){
        for(City c: cities){
            if(c.getName().equals(city))
                return c.getId();
        }
        return 0;
    }

    public static String getCity(int id){
        return cities.get(id-1).getName();
    }
}

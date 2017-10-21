package lesh.egresatecnm.com.http.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import lesh.egresatecnm.com.http.Api.API;
import lesh.egresatecnm.com.http.Api.ApiServices.WeatherServices;
import lesh.egresatecnm.com.http.Models.City;
import lesh.egresatecnm.com.http.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WeatherServices services= API.getApi().create(WeatherServices.class);
        Call<City> cityCall=services.getCityCelsius("Seville,ES",API.APPKEY,"metric");

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city=response.body();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

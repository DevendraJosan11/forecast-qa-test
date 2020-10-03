package com.retrofit.forecast.api;


import com.retrofit.forecast.model.City;
import com.retrofit.forecast.model.Forecast;
import com.retrofit.forecast.util.PathDate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ForecastService {

    @GET("/api/location/{city_id}/{date}/")
    Call<List<Forecast>> getForecast(@Path("city_id") Long cityId, @Path("date") PathDate date);

    @GET("/api/location/search/")
    Call<List<City>> findCityByName(@Query("query") String city);
}

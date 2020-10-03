package com.retrofit.forecast.steps;

import com.retrofit.forecast.api.ForecastService;
import com.retrofit.forecast.model.City;
import com.retrofit.forecast.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Response;

import java.util.List;

public abstract class BaseSteps <T extends BaseSteps<T>> {

    Response<List<Forecast>> forecastResponse;
    Response<List<City>> cityResponse;

    @Autowired
    ForecastService forecastService;
}

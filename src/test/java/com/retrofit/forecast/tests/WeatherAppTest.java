package com.retrofit.forecast.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;


@Epic("Weather app tests ")
@Feature("Weather app tests ")
@DisplayName("Weather app tests : Tests to verify weather api")
public class WeatherAppTest extends BaseTest {

    @Test
    @DisplayName("should retrieve weather details using city id and date")
    void retrieveWeatherForecastWithCityIdAndDate() throws IOException {
        weatherDetailsSteps
                .givenIHaveMockResponseForCityIdAndDate(server)
                .whenIRetrieveWeatherDetailsByCityIdAndDate()
                .thenIVerifyWeatherDetails();
    }

    @Test
    @DisplayName("should retrieve weather details by city name")
    void retrieveWeatherDetailsByCityName() throws IOException {
        weatherDetailsSteps
                .givenIHaveMockResponseForCityName(server)
                .whenIRetrieveWeatherDetailsByCityName()
                .thenIVerifyWeatherDetailsForCity();

    }

}
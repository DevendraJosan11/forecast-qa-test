package com.retrofit.forecast.steps;

import com.retrofit.forecast.model.City;
import com.retrofit.forecast.model.Forecast;
import com.retrofit.forecast.util.PathDate;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.retrofit.forecast.utils.JsonUtils.getJsonStringFromPojo;
import static org.junit.jupiter.api.Assertions.*;

@Component
@Slf4j
public class WeatherDetailsSteps extends BaseSteps<WeatherDetailsSteps>{


    @Step
    public WeatherDetailsSteps givenIHaveMockResponseForCityIdAndDate(MockWebServer server) {
        List<Forecast> forecast = Collections.singletonList(new Forecast()
                .setId(2L)
                .setHumidity(61)
                .setWindSpeed(7.6)
                .setTemperature(26.5)
                .setWeatherState("Clear"));

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type","application/json; charset=utf-8")
                .setBody(getJsonStringFromPojo(forecast));
        server.enqueue(mockResponse);
    return this;
    }

    @Step
    public WeatherDetailsSteps givenIHaveMockResponseForCityName(MockWebServer server) {
        List<City> city = Collections.singletonList(new City()
                .setWoeid(1121L)
                .setTitle("Dubai"));
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type","application/json; charset=utf-8")
                .setBody(getJsonStringFromPojo(city));
        server.enqueue(mockResponse);
    return this;
    }

    @Step
    public WeatherDetailsSteps whenIRetrieveWeatherDetailsByCityIdAndDate() throws IOException {
        PathDate pathDate = new PathDate(LocalDate.now().plusDays(5));
        Call<List<Forecast>> forecastCall =forecastService.getForecast(2L, pathDate);
        forecastResponse = forecastCall.execute();

        return this;
    }

    @Step
    public WeatherDetailsSteps whenIRetrieveWeatherDetailsByCityName() throws IOException {
        Call<List<City>> forecastCall =forecastService.findCityByName("Dubai");
        cityResponse = forecastCall.execute();
        return this;
    }

    @Step
    public WeatherDetailsSteps thenIVerifyWeatherDetails(){
        assertTrue(forecastResponse.isSuccessful());
        List<Forecast> response = forecastResponse.body();
        log.info("***************** Response **************************" + getJsonStringFromPojo(response));
        assertNotNull(response,"verify response");

        assertEquals(61,response.stream().findFirst().get().getHumidity(),"verify humidity");
        assertEquals(7.6,response.stream().findFirst().get().getWindSpeed(),"verify windSpeed");
        assertEquals(26.5,response.stream().findFirst().get().getTemperature(),"verify temprature");
        assertTrue(response.stream().findFirst().get().getWeatherState().equals("Clear"),"verify weather state");

        return this;
    }

    @Step
    public WeatherDetailsSteps thenIVerifyWeatherDetailsForCity(){
        assertTrue(cityResponse.isSuccessful());
        List<City> response = cityResponse.body();
        log.info("*********************** Response **************" + getJsonStringFromPojo(response));
        assertNotNull(cityResponse,"verify response");

        assertAll(
                ()-> assertEquals("Dubai",response.stream().findFirst().get().getTitle(),"verify title"),
                ()-> assertEquals(1121,response.stream().findFirst().get().getWoeid(),"verify woeid")
        );
        return this;

    }

}

package com.retrofit.forecast;

import com.retrofit.forecast.api.ForecastService;
import com.retrofit.forecast.model.City;
import com.retrofit.forecast.model.Forecast;
import com.retrofit.forecast.util.PathDate;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.retrofit.forecast.JsonUtils.getJsonStringFromPojo;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {

    @Autowired
    ForecastService forecastService;

    @Autowired
    JsonUtils jsonUtils;

    public MockWebServer server;

    @Before
    public void before() throws IOException {
        log.info("============= Starting mock server===============");
        server = new MockWebServer();
        server.start(8080);

    }

    @After
    public void after() throws IOException {
        log.info("=========Closing MockWebServer===========");
        server.close();
    }

    @Test
    public void retrieveWeatherForecastWithCityIdAndDate() throws IOException {

        PathDate pathDate = new PathDate(LocalDate.now().plusDays(5));

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

        Call<List<Forecast>> forecastCall =forecastService.getForecast(2L, pathDate);

        Response<List<Forecast>> forecastResponse = forecastCall.execute();
        assertTrue(forecastResponse.isSuccessful());
        List<Forecast> response = forecastResponse.body();
        log.info("************Response*********" + getJsonStringFromPojo(response));
        assertNotNull(response,"verify response");
        assertEquals(61,response.stream().findFirst().get().getHumidity(),"verify humidity");
        assertEquals(7.6,response.stream().findFirst().get().getWindSpeed(),"verify windSpeed");
        assertEquals(26.5,response.stream().findFirst().get().getTemperature(),"verify temprature");
        assertTrue(response.stream().findFirst().get().getWeatherState().equals("Clear"),"verify weather state");
    }


    @Test
    public void retrieveDetailsByCityName() throws IOException {

        PathDate pathDate = new PathDate(LocalDate.now());

        List<City> city = Collections.singletonList(new City()
                .setWoeid(1121L)
                .setTitle("Dubai"));

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type","application/json; charset=utf-8")
                .setBody(getJsonStringFromPojo(city));

        server.enqueue(mockResponse);

        Call<List<City>> forecastCall =forecastService.findCityByName("Dubai");
        Response<List<City>> cityResponse = forecastCall.execute();
        List<City> response = cityResponse.body();
        log.info("*********************** Response **************" + getJsonStringFromPojo(response));

        assertNotNull(cityResponse,"verify response");

        assertAll(
                ()-> assertEquals("Dubai",response.stream().findFirst().get().getTitle(),"verify title"),
                ()-> assertEquals(1121,response.stream().findFirst().get().getWoeid(),"verify woeid")
        );
    }

}
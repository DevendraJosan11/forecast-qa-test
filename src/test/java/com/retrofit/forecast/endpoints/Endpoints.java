package com.retrofit.forecast.endpoints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Endpoints {

    @Value("${api.url.weather}")
    private String getWeatherApiUrl;

    public String retrieveWeatherPath() {
        return getWeatherApiUrl;
    }
}

package com.retrofit.forecast.tests;

import com.retrofit.forecast.api.ForecastService;
import com.retrofit.forecast.config.Config;
import com.retrofit.forecast.endpoints.Endpoints;
import com.retrofit.forecast.steps.WeatherDetailsSteps;
import com.retrofit.forecast.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ContextConfiguration(classes = Config.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource({ "classpath:endpoints.properties" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @Autowired
    JsonUtils jsonUtils;

    @Autowired
    ForecastService forecastService;

    @Autowired
    WeatherDetailsSteps weatherDetailsSteps;

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        log.info("********* Start of the Test : " + testInfo.getDisplayName()+" ****************");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        log.info("********** End of the Test : " +testInfo.getDisplayName()+ " *****************");
    }

    public MockWebServer server;

    @BeforeAll
    public void before() throws IOException {
        log.info("============= Starting mock server===============");
        server = new MockWebServer();
        server.start(8080);

    }

    @AfterAll
    public void after() throws IOException {
        log.info("=========Closing MockWebServer===========");
        server.close();
    }

}

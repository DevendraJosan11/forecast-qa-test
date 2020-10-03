package com.retrofit.forecast;

import com.retrofit.forecast.api.FakeInterceptor;
import com.retrofit.forecast.api.ForecastService;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class App {

    /*public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Pass city name as an argument");
            System.exit(1);
        }*/

        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }

        @Value("${api.url.weather}")
        private String weatherUrl;

        @Bean
        public OkHttpClient okHttpClient() {
            return new OkHttpClient.Builder().addInterceptor(new FakeInterceptor()).build();
        }

        @Bean
        public Retrofit retrofitForecast() {

            return new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl(weatherUrl)
                    .client(okHttpClient())
                    .build();
        }

        @Bean
        public ForecastService forecastService() { return retrofitForecast().create(ForecastService.class);
        }


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        /*LocalDate tomorrow = LocalDate.now().plusDays(1);

        ForecastServiceImpl service = new ForecastServiceImpl(retrofitForecast());*/
        //System.out.println(service.getForecast(args[0], tomorrow));*/

}

package finalmission.feature.weather.client;

import finalmission.feature.weather.dto.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
public class WeatherApiClient {

    private final RestClient weatherClient;
    private final String weatherApiSecret;

    public WeatherApiClient(RestClient weatherClient, @Value("${weather-api.secret-key}")String weatherApiSecret) {
        this.weatherClient = weatherClient;
        this.weatherApiSecret = weatherApiSecret;
    }

    public WeatherApiResponse getSongPaWeather(){

        String localDate = LocalDate.now().toString().replace("-", "");

        return weatherClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getVilageFcst")
                        .queryParam("serviceKey", "")
                        .queryParam("dataType", "JSON")
                        .queryParam("pageNo", 1)
                        .queryParam("numOfRows", 10)
                        .queryParam("base_date",localDate)
                        .queryParam("base_time", "0500")
                        .queryParam("nx", 55)
                        .queryParam("ny", 127)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(WeatherApiResponse.class);
    }
}

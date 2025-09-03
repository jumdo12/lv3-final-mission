package finalmission.feature.weather.service;

import finalmission.feature.weather.client.WeatherApiClient;
import finalmission.feature.weather.domain.Weather;
import finalmission.feature.weather.dto.WeatherApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherApiClient weatherApiClient;

    public Weather getSongPaCurrWeather() {
        WeatherApiResponse songPaWeather = weatherApiClient.getSongPaWeather();
        String sky = parsePty(songPaWeather);
        String pop = parsePop(songPaWeather);
        System.out.println(new Weather(sky, pop));
        return new Weather(sky, pop);
    }

    private String parsePty(WeatherApiResponse weatherApiResponse) {
        List<WeatherApiResponse.Response.Body.Items.Item> items = weatherApiResponse.getResponse().getBody().getItems().getItem();
        WeatherApiResponse.Response.Body.Items.Item sky = items.stream().filter(item -> item.getCategory().equals("PTY")).findFirst()
                .orElseThrow(() -> new IllegalStateException("카테고리를 찾을 수 없습니다 : PTY"));

        if(sky.getFcstValue().equals("1")){
            return "비";
        }
        if(sky.getFcstValue().equals("2")){
            return "비/눈";
        }
        if(sky.getFcstValue().equals("3")){
            return "눈";
        }
        if(sky.getFcstValue().equals("4")){
            return "소나기";
        }

        return "없음";
    }

    private String parsePop(WeatherApiResponse weatherApiResponse) {
        List<WeatherApiResponse.Response.Body.Items.Item> items = weatherApiResponse.getResponse().getBody().getItems().getItem();
        WeatherApiResponse.Response.Body.Items.Item pop = items.stream().filter(item -> item.getCategory().equals("POP")).findFirst()
                .orElseThrow(() -> new IllegalStateException("카테고리를 찾을 수 없습니다 : POP"));
        return pop.getFcstValue() + "%";
    }
}

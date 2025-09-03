package finalmission.feature.weather.controller;

import finalmission.feature.weather.domain.Weather;
import finalmission.feature.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "날씨 API", description = "날씨 관련 API")
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(summary = "현재 날씨 조회", description = "현재 송파구의 날씨를 조회합니다.")
    @GetMapping("/weathers")
    public ResponseEntity<Weather> currentSongPaWeather() {
        Weather songPaCurrWeather = weatherService.getSongPaCurrWeather();
        return ResponseEntity.ok(songPaCurrWeather);
    }
}

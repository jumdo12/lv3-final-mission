package finalmission.feature.weather.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Weather {

    private final String pty;
    private final String pop;
}

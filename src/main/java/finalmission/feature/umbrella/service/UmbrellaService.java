package finalmission.feature.umbrella.service;

import finalmission.feature.umbrella.domain.Umbrella;
import finalmission.feature.umbrella.dto.response.UmbrellaResponse;
import finalmission.feature.umbrella.repository.UmbrellaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UmbrellaService {

    private final UmbrellaRepository umbrellaRepository;

    public UmbrellaResponse createUmbrella() {
        Umbrella umbrella = Umbrella.create();
        Umbrella saveUmbrella = umbrellaRepository.save(umbrella);
        return new UmbrellaResponse(saveUmbrella.getId());
    }
}

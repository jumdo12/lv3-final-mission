package finalmission.feature.umbrella.controller;

import finalmission.feature.umbrella.dto.response.UmbrellaResponse;
import finalmission.feature.umbrella.service.UmbrellaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "우산 API", description = "우산 관련 API")
@RestController
@RequiredArgsConstructor
public class UmbrellaController {

    private final UmbrellaService umbrellaService;

    @Operation(summary = "우산 등록", description = "우산을 100개 등록합니다.")
    @PostMapping("/umbrellas")
    public ResponseEntity<Void> registerUmbrella(){
        for(int i=0; i < 100; i++){
            UmbrellaResponse umbrella = umbrellaService.createUmbrella();
        }
        return ResponseEntity.ok().build();
    }
}

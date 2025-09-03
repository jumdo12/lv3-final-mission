package finalmission.feature.umbrella.controller;

import finalmission.feature.umbrella.dto.response.UmbrellaResponse;
import finalmission.feature.umbrella.service.UmbrellaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UmbrellaController {

    private final UmbrellaService umbrellaService;

    @PostMapping("/umbrellas")
    public ResponseEntity<Void> registerUmbrella(){
        for(int i=0; i < 100; i++){
            UmbrellaResponse umbrella = umbrellaService.createUmbrella();
        }
        return ResponseEntity.ok().build();
    }
}

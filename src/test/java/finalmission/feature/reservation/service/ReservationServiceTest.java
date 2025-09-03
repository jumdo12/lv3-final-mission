package finalmission.feature.reservation.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ReservationServiceTest {

    @Test
    void 의미없는_테스트() {
        var hello = "Hello";

        Assertions.assertEquals(hello, "Hello");
    }
}
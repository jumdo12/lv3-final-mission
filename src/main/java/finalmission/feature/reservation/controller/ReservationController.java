package finalmission.feature.reservation.controller;

import finalmission.feature.reservation.domain.Reservation;
import finalmission.feature.reservation.dto.request.ReservationsFindAllRequest;
import finalmission.feature.reservation.dto.request.ReservationRequest;
import finalmission.feature.reservation.dto.response.ReservationResponse;
import finalmission.feature.reservation.service.ReservationService;
import finalmission.global.auth.annotaion.LoginCustomer;
import finalmission.global.auth.dto.CustomerLoginRequest;
import finalmission.global.auth.dto.LoginCustomerInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "예약 API", description = "예약 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "예약 생성", description = "예약을 생성합니다.")
    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {
        long startTime = System.currentTimeMillis();
        log.info("[API_REQ] 예약 생성 요청 진입: reservationDate={}", reservationRequest.reservationDate());

        Reservation reservation = reservationService.createReservation(reservationRequest);
        ReservationResponse reservationResponse = ReservationResponse.from(reservation);

        long endTime = System.currentTimeMillis();
        log.info("[API_RES] 예약 생성 요청 완료: reservationId={}, 처리시간={}ms", reservationResponse.reservationId(), endTime - startTime);
        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId())).body(reservationResponse);
    }

    @Operation(summary = "모든 예약 조회", description = "특정 날짜의 모든 예약을 조회합니다.")
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations(@RequestBody ReservationsFindAllRequest request) {
        List<Reservation> allReservations = reservationService.findAllReservations(request.localDate());
        List<ReservationResponse> reservationResponses = allReservations.stream()
                .map(ReservationResponse::from)
                .toList();
        return ResponseEntity.ok(reservationResponses);
    }

    @Operation(summary = "내 예약 조회", description = "내 예약을 조회합니다.")
    @GetMapping("/reservations-mine")
    public ResponseEntity<List<ReservationResponse>> getAllReservationsMine(@LoginCustomer LoginCustomerInfo info, @RequestBody ReservationRequest request) {
        List<Reservation> reservations = reservationService.findReservations(info, request);
        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(ReservationResponse::from)
                .toList();
        return ResponseEntity.ok(reservationResponses);
    }

    @Operation(summary = "예약 삭제", description = "예약을 삭제합니다.")
    @DeleteMapping("/reservations")
    public ResponseEntity<Void> deleteReservation(@LoginCustomer LoginCustomerInfo info, @RequestBody ReservationRequest reservationRequest) {
        reservationService.deleteReservation(info, reservationRequest);
        return ResponseEntity.noContent().build();
    }
}

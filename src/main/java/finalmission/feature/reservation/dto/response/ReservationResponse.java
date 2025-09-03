package finalmission.feature.reservation.dto.response;

import finalmission.feature.customer.dto.response.CustomerResponse;
import finalmission.feature.reservation.domain.Reservation;
import finalmission.feature.umbrella.dto.response.UmbrellaResponse;

import java.time.LocalDate;

public record ReservationResponse(long reservationId, UmbrellaResponse umbrellaResponse, LocalDate reservationDate) {

    public static ReservationResponse from(Reservation reservation) {
        UmbrellaResponse umbrellaResponse = new UmbrellaResponse(reservation.getUmbrella().getId());
        return new ReservationResponse(reservation.getId(), umbrellaResponse, reservation.getReservationDate());
    }
}

package finalmission.feature.reservation.repository;

import finalmission.feature.customer.domain.Customer;
import finalmission.feature.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    void deleteByReservationDate(LocalDate reservationDate);

    List<Reservation> findByReservationDate(LocalDate reservationDate);
}

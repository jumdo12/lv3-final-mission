package finalmission.feature.reservation.domain;

import finalmission.feature.customer.domain.Customer;
import finalmission.feature.umbrella.domain.Umbrella;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Umbrella umbrella;

    private LocalDate reservationDate;

    private Reservation(Long id, Umbrella umbrella, LocalDate reservationDate) {
        this.id = id;
        this.umbrella = umbrella;
        this.reservationDate = reservationDate;
    }

    public static Reservation createWithoutId(Umbrella umbrella, LocalDate reservationDate) {
        return new Reservation(null,umbrella, reservationDate);
    }
}

package finalmission.feature.reservation.service;

import finalmission.feature.customer.domain.Customer;
import finalmission.feature.customer.repository.CustomerRepository;
import finalmission.feature.reservation.domain.Reservation;
import finalmission.feature.reservation.dto.request.ReservationRequest;
import finalmission.feature.reservation.repository.ReservationRepository;
import finalmission.feature.umbrella.domain.Umbrella;
import finalmission.feature.umbrella.repository.UmbrellaRepository;
import finalmission.global.auth.dto.LoginCustomerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final UmbrellaRepository umbrellaRepository;

    public Reservation createReservation(ReservationRequest request) {
        log.debug("[SVC_STEP] 예약 가능한 우산 목록 조회 시도: reservationDate={}", request.reservationDate());

        List<Umbrella> availableUmbrellas = umbrellaRepository.findAvailableUmbrella(request.reservationDate());

        if(availableUmbrellas.isEmpty()) {
            log.warn("[SVC_FAIL] 예약 생성 실패 - 예약 가능한 우산 목록이 비어있음: reservationDate={}", request.reservationDate());
            throw new IllegalStateException("예약 가능한 우산이 없습니다");
        }

        Umbrella umbrella = availableUmbrellas.stream().findFirst().get();
        //Umbrella umbrella= umbrellaRepository.findUmbrellasById(1L).orElseThrow(() -> new IllegalArgumentException(""));

        Reservation withoutId = Reservation.createWithoutId(umbrella, request.reservationDate());
        log.debug("[SVC_STEP] 예약 가능한 우산 찾음: umbrellaId={}", umbrella.getId());
        return reservationRepository.save(withoutId);
    }
    
    public List<Reservation> findReservations(LoginCustomerInfo loginCustomerInfo, ReservationRequest request){
        Customer customer = findCustomer(loginCustomerInfo.id());
        return reservationRepository.findByReservationDate(request.reservationDate());
    }

    public List<Reservation> findAllReservations(LocalDate reservationDate){
        return reservationRepository.findByReservationDate(reservationDate);
    }

    public void deleteReservation(LoginCustomerInfo loginCustomerInfo, ReservationRequest request) {
        Customer customer = findCustomer(loginCustomerInfo.id());
        reservationRepository.deleteByReservationDate(request.reservationDate());
    }

    private Customer findCustomer(long userId){
        return customerRepository
                .findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 Customer를 찾을 수 없습니다. ID : " + userId));
    }
}


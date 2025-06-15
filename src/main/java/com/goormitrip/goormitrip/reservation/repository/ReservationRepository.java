package com.goormitrip.goormitrip.reservation.repository;

import com.goormitrip.goormitrip.reservation.domain.Reservation;
import com.goormitrip.goormitrip.reservation.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByProductIdAndStatus(Long productId, ReservationStatus status);
    List<Reservation> findByProductIdAndTravelDate(Long productId, LocalDate travelDate);
}


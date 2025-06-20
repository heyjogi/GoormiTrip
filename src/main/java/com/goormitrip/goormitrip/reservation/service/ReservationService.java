package com.goormitrip.goormitrip.reservation.service;

import java.time.LocalDate;
import java.util.List;

import com.goormitrip.goormitrip.reservation.dto.ReservationCancelResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateResponse;

public interface ReservationService {
    List<LocalDate> getAvailableDates(Long productId);

    ReservationResponse createReservation(ReservationRequest request);

    ReservationUpdateResponse updateReservation(String reservationId, ReservationUpdateRequest request, Long userId);

    ReservationCancelResponse cancelReservation(String reservationId);
}

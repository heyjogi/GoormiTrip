package com.goormitrip.goormitrip.product.service;

import java.time.LocalDate;
import java.util.List;

import com.goormitrip.goormitrip.product.dto.ReservationCancelResponse;
import com.goormitrip.goormitrip.product.dto.ReservationRequest;
import com.goormitrip.goormitrip.product.dto.ReservationResponse;
import com.goormitrip.goormitrip.product.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.product.dto.ReservationUpdateResponse;

public interface ReservationService {
    List<LocalDate> getAvailableDates(Long productId);

    ReservationResponse createReservation(ReservationRequest request, Long userId);

    ReservationUpdateResponse updateReservation(String reservationId, ReservationUpdateRequest request, Long userId);

    ReservationCancelResponse cancelReservation(String reservationId, Long userId);
}

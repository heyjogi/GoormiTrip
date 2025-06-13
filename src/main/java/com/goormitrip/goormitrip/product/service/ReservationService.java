package com.goormitrip.goormitrip.product.service;

import java.time.LocalDate;
import java.util.List;

import com.goormitrip.goormitrip.product.dto.ReservationRequest;
import com.goormitrip.goormitrip.product.dto.ReservationResponse;

public interface ReservationService {
    List<LocalDate> getAvailableDates(Long productId);

    ReservationResponse createReservation(ReservationRequest request, Long userId);
}

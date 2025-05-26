package com.goormitrip.goormitrip.product.service.impl;

import com.goormitrip.goormitrip.product.domain.Reservation;
import com.goormitrip.goormitrip.product.domain.ReservationStatus;
import com.goormitrip.goormitrip.product.repository.ReservationRepository;
import com.goormitrip.goormitrip.product.service.ReservationService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<LocalDate> getAvailableDates(Long productId) {
        List<Reservation> reservations = reservationRepository.findByProductIdAndStatus(productId, ReservationStatus.RESERVED);

        Set<LocalDate> reservedDates = reservations.stream()
            .map(Reservation::getTravelDate)
            .collect(Collectors.toSet());

        LocalDate today = LocalDate.now();
        return today.datesUntil(today.plusDays(30))
            .filter(date -> !reservedDates.contains(date))
            .collect(Collectors.toList());
    }
}

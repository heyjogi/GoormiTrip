package com.goormitrip.goormitrip.product.service.impl;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.Reservation;
import com.goormitrip.goormitrip.product.domain.ReservationStatus;
import com.goormitrip.goormitrip.product.dto.ReservationRequest;
import com.goormitrip.goormitrip.product.dto.ReservationResponse;
import com.goormitrip.goormitrip.product.exception.InvalidPeopleCountException;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;
import com.goormitrip.goormitrip.product.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ProductRepository productRepository) {
        this.reservationRepository = reservationRepository;
        this.productRepository = productRepository;
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

    @Override
    public ReservationResponse createReservation (ReservationRequest request, Long userId) {
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ProductNotFoundException(request.getProductId()));
        if (request.getPeopleCount() < product.getMinPeople() || request.getPeopleCount() > product.getMaxPeople()) {
            throw new InvalidPeopleCountException(product.getMinPeople(), product.getMaxPeople());
        }

        Reservation reservation = new Reservation();
        reservation.setProduct(product);
        reservation.setTravelDate(request.getTravelDate());
        reservation.setPeopleCount(request.getPeopleCount());
        reservation.setStatus(ReservationStatus.RESERVED);

        Reservation saved = reservationRepository.save(reservation);

        return ReservationResponse.builder()
            .success(true)
            .response(ReservationResponse.ResponseInfo.builder()
                .status("reserved")
                .message("예약이 완료되었습니다.")
                .build())
            .data(ReservationResponse.Data.builder()
                .reservationId("A" + saved.getId())
                .productId(product.getId())
                .createdAt(saved.getCreatedAt())
                .travelDate(saved.getTravelDate())
                .peopleCount(saved.getPeopleCount())
                .build())
            .build();



    }
}

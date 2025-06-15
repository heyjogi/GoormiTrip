package com.goormitrip.goormitrip.reservation.service.impl;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.reservation.domain.Reservation;
import com.goormitrip.goormitrip.reservation.domain.ReservationStatus;
import com.goormitrip.goormitrip.reservation.dto.ReservationCancelResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateResponse;
import com.goormitrip.goormitrip.reservation.exception.InvalidPeopleCountException;
import com.goormitrip.goormitrip.reservation.exception.InvalidTravelDateException;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;
import com.goormitrip.goormitrip.reservation.exception.ReservationAlreadyCancelledException;
import com.goormitrip.goormitrip.reservation.exception.ReservationCancelDeadlineExpiredException;
import com.goormitrip.goormitrip.reservation.exception.ReservationChangeDeadlineExpiredException;
import com.goormitrip.goormitrip.reservation.exception.ReservationNotFoundException;
import com.goormitrip.goormitrip.product.repository.ProductRepository;
import com.goormitrip.goormitrip.reservation.repository.ReservationRepository;
import com.goormitrip.goormitrip.reservation.service.ReservationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            .orElseThrow(() -> new ProductNotFoundException());
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
            .reservationId("A" + saved.getId())
            .userId(userId)
            .productId(product.getId())
            .createdAt(saved.getCreatedAt())
            .travelDate(saved.getTravelDate())
            .peopleCount(saved.getPeopleCount())
            .build();
    }

    @Override
    @Transactional
    public ReservationUpdateResponse updateReservation(String reservationId, ReservationUpdateRequest request,
        Long userId) {
        Long id = Long.parseLong(reservationId.replace("A", ""));

        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException());

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new ReservationAlreadyCancelledException();
        }

        Product product = reservation.getProduct();

        if (request.getPeopleCount() < product.getMinPeople() || request.getPeopleCount() > product.getMaxPeople()) {
            throw new InvalidPeopleCountException(product.getMinPeople(), product.getMaxPeople());
        }

        LocalDate travelDate = request.getTravelDate();
        if (travelDate.isBefore(LocalDate.now().plusDays(2))) {
            throw new ReservationChangeDeadlineExpiredException();
        }

        boolean dateReserved = reservationRepository.findByProductIdAndTravelDate(product.getId(), travelDate)
            .stream()
            .anyMatch(r -> r.getStatus() == ReservationStatus.RESERVED && !r.getId().equals(reservation.getId()));

        if (dateReserved) {
            throw new InvalidTravelDateException();
        }

        reservation.setTravelDate(travelDate);
        reservation.setPeopleCount(request.getPeopleCount());

        return ReservationUpdateResponse.builder()
            .reservationId("A" + reservation.getId())
            .userId(userId)
            .productId(product.getId())
            .updatedAt(reservation.getUpdatedAt())
            .travelDate(travelDate)
            .peopleCount(request.getPeopleCount())
            .build();
    }

    @Override
    @Transactional
    public ReservationCancelResponse cancelReservation(String reservationId, Long userId) {
        Long id = Long.parseLong(reservationId.replace("A", ""));

        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException());

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new ReservationAlreadyCancelledException();
        }

        LocalDate today = LocalDate.now();
        if (reservation.getTravelDate().isBefore(today.plusDays(3))) {
            throw new ReservationCancelDeadlineExpiredException();
        }

        reservation.setStatus(ReservationStatus.CANCELLED);

        return ReservationCancelResponse.builder()
            .reservationId("A" + reservation.getId())
            .updatedAt(reservation.getUpdatedAt())
            .build();

    }
}

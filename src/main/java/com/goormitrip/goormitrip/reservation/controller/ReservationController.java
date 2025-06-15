package com.goormitrip.goormitrip.reservation.controller;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.reservation.dto.ReservationRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateResponse;
import com.goormitrip.goormitrip.reservation.service.ReservationService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/available")
    public List<LocalDate> getAvailableDates(@RequestParam Long productId) {
        return reservationService.getAvailableDates(productId);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
        @RequestBody ReservationRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        ReservationResponse response = reservationService.createReservation(request, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ReservationUpdateResponse> updateReservation(
        @PathVariable String reservationId,
        @RequestBody ReservationUpdateRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        ReservationUpdateResponse response = reservationService.updateReservation(reservationId, request, userId);
        return ResponseEntity.ok(response);
    }

}
package com.goormitrip.goormitrip.reservation.controller;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<LocalDate>>> getAvailableDates(@RequestParam Long productId) {
        List<LocalDate> availableDates = reservationService.getAvailableDates(productId);
        return ApiResponse.ok(availableDates);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservationResponse>> createReservation(
            @RequestBody ReservationRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        ReservationResponse response = reservationService.createReservation(request, userId);
        return ApiResponse.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ReservationUpdateResponse>> updateReservation(
            @RequestParam String reservationId,
            @RequestBody ReservationUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        ReservationUpdateResponse response = reservationService.updateReservation(reservationId, request, userId);
        return ApiResponse.ok(response);
    }
}
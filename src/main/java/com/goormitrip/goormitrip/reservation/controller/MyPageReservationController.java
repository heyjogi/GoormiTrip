package com.goormitrip.goormitrip.reservation.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationCancelResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateResponse;
import com.goormitrip.goormitrip.reservation.service.ReservationService;

@RestController
@RequestMapping("/mypage/reservations")
public class MyPageReservationController {

    private final ReservationService reservationService;

    public MyPageReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ApiResponse<ReservationUpdateResponse>> updateReservation(
        @PathVariable String reservationId,
        @RequestBody ReservationUpdateRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        ReservationUpdateResponse response = reservationService.updateReservation(reservationId, request, userId);
        return ApiResponse.ok(response);
    }

    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<ApiResponse<ReservationCancelResponse>> cancelReservation(
        @PathVariable String reservationId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        ReservationCancelResponse response = reservationService.cancelReservation(reservationId);
        return ApiResponse.ok(response);
    }
}
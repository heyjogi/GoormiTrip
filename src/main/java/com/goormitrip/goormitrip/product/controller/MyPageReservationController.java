package com.goormitrip.goormitrip.product.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.product.dto.ReservationCancelResponse;
import com.goormitrip.goormitrip.product.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.product.dto.ReservationUpdateResponse;
import com.goormitrip.goormitrip.product.service.ReservationService;

@RestController
@RequestMapping("/mypage/reservations")
public class MyPageReservationController {

    private final ReservationService reservationService;

    public MyPageReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationUpdateResponse> updateReservation(
        @PathVariable String reservationId,
        @RequestBody ReservationUpdateRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        ReservationUpdateResponse response = reservationService.updateReservation(reservationId, request, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<ReservationCancelResponse> cancelReservation(
        @PathVariable String reservationId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        ReservationCancelResponse response = reservationService.cancelReservation(reservationId, userId);
        return ResponseEntity.ok(response);
    }

}
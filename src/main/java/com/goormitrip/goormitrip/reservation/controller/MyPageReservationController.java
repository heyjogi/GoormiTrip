package com.goormitrip.goormitrip.reservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationCancelResponse;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateRequest;
import com.goormitrip.goormitrip.reservation.dto.ReservationUpdateResponse;
import com.goormitrip.goormitrip.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

import com.goormitrip.goormitrip.reservation.dto.MyReservationResponse;
import com.goormitrip.goormitrip.reservation.service.impl.MyReservationServiceImpl;

@RestController
@RequestMapping("/mypage/reservations")
@RequiredArgsConstructor
public class MyPageReservationController {

    private final ReservationService reservationService;
    private final MyReservationServiceImpl myReservationService;

    @PutMapping("/{reservationId}")
    public ResponseEntity<ApiResponse<ReservationUpdateResponse>> updateReservation(
            @PathVariable String reservationId,
            @RequestBody ReservationUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        ReservationUpdateResponse response = reservationService.updateReservation(reservationId, request, userId);
        return ApiResponse.ok(response);
    }

    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<ApiResponse<ReservationCancelResponse>> cancelReservation(
            @PathVariable String reservationId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        ReservationCancelResponse response = reservationService.cancelReservation(reservationId);
        return ApiResponse.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MyReservationResponse>>> getReservations(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<MyReservationResponse> result = myReservationService.getReservationsByUserId(userId);
        return ApiResponse.ok(result);
    }
}

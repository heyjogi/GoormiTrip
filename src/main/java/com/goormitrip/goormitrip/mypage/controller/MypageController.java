package com.goormitrip.goormitrip.mypage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.mypage.dto.MyReservationResponse;
import com.goormitrip.goormitrip.mypage.service.MyReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MyReservationService myReservationService;

    @GetMapping("/reservations")
    public ResponseEntity<ApiResponse<List<MyReservationResponse>>> getReservations(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<MyReservationResponse> result = myReservationService.getReservationsByUserId(userId);
        return ApiResponse.ok(result);
    }
}
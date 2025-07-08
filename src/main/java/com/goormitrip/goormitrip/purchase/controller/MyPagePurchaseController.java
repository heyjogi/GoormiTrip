package com.goormitrip.goormitrip.purchase.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.purchase.dto.MyPurchaseHistoryResponse;
import com.goormitrip.goormitrip.purchase.service.MyPurchaseService;

@RestController
@RequestMapping("/mypage/purchases")
public class MyPagePurchaseController {

    private final MyPurchaseService myPurchaseService;

    public MyPagePurchaseController(MyPurchaseService myPurchaseService) {
        this.myPurchaseService = myPurchaseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MyPurchaseHistoryResponse>>> getPurchaseHistory(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<MyPurchaseHistoryResponse> result = myPurchaseService.getPurchaseHistoryByUserId(userId);
        return ApiResponse.ok(result);
    }
}

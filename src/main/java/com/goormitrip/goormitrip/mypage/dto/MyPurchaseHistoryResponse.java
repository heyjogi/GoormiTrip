package com.goormitrip.goormitrip.mypage.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MyPurchaseHistoryResponse {
    private String productTitle;
    private String thumbnailUrl;
    private LocalDate purchaseDate;
    private Integer amount;
    private String paymentMethod;
    private String paymentStatus;
}

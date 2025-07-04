package com.goormitrip.goormitrip.mypage.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class MyReservationResponse {
    private Long reservationId;
    private String productName;
    private String thumbnailUrl;
    private LocalDate travelDate;
    private int peopleCount;
    private String reservationStatus;
    private String paymentStatus;
    private String paymentMethod;
    private BigDecimal amount;
    private LocalDateTime paidAt;

    public MyReservationResponse(Long reservationId, String productTitle, String productThumbnail,
            LocalDate travelDate, int peopleCount, String reservationStatus,
            String status, String method, BigDecimal amount, LocalDateTime paidAt) {
        this.reservationId = reservationId;
        this.productName = productTitle;
        this.thumbnailUrl = productThumbnail;
        this.travelDate = travelDate;
        this.peopleCount = peopleCount;
        this.reservationStatus = reservationStatus;
        this.paymentStatus = status;
        this.paymentMethod = method;
        this.amount = amount;
        this.paidAt = paidAt;
    }
}

package com.goormitrip.goormitrip.mypage.dto;

import com.goormitrip.goormitrip.payment.domain.TossPaymentMethod;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;
import com.goormitrip.goormitrip.reservation.domain.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyReservationResponse {

    private Long reservationId;
    private String productName;
    private String productThumbnail;
    private LocalDate travelDate;
    private Integer peopleCount;
    private ReservationStatus reservationStatus;
    private TossPaymentMethod tossPaymentMethod;
    private TossPaymentStatus tossPaymentStatus;
    private BigDecimal totalAmount;
    private LocalDateTime approvedAt;

    public MyReservationResponse(Long reservationId, String productName, String productThumbnail,
            LocalDate travelDate, Integer peopleCount, ReservationStatus reservationStatus,
            TossPaymentMethod tossPaymentMethod, TossPaymentStatus tossPaymentStatus,
            BigDecimal totalAmount, LocalDateTime approvedAt) {
        this.reservationId = reservationId;
        this.productName = productName;
        this.productThumbnail = productThumbnail;
        this.travelDate = travelDate;
        this.peopleCount = peopleCount;
        this.reservationStatus = reservationStatus;
        this.tossPaymentMethod = tossPaymentMethod;
        this.tossPaymentStatus = tossPaymentStatus;
        this.totalAmount = totalAmount;
        this.approvedAt = approvedAt;
    }
}
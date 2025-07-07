package com.goormitrip.goormitrip.mypage.dto;

import com.goormitrip.goormitrip.payment.domain.TossPaymentMethod;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyPurchaseHistoryResponse {

    private String productTitle;
    private String productThumbnail;
    private LocalDateTime approvedAt;
    private Long totalAmount;
    private TossPaymentMethod tossPaymentMethod;
    private TossPaymentStatus tossPaymentStatus;

    public MyPurchaseHistoryResponse(
            String productTitle,
            String productThumbnail,
            LocalDateTime approvedAt,
            Long totalAmount,
            TossPaymentMethod tossPaymentMethod,
            TossPaymentStatus tossPaymentStatus) {
        this.productTitle = productTitle;
        this.productThumbnail = productThumbnail;
        this.approvedAt = approvedAt;
        this.totalAmount = totalAmount;
        this.tossPaymentMethod = tossPaymentMethod;
        this.tossPaymentStatus = tossPaymentStatus;
    }
}
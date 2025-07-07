package com.goormitrip.goormitrip.purchase.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.purchase.dto.MyPurchaseHistoryResponse;
import com.goormitrip.goormitrip.purchase.service.MyPurchaseService;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;
import com.goormitrip.goormitrip.reservation.repository.ReservationRepository;

@Service
public class MyPurchaseServiceImpl implements MyPurchaseService {

    private final ReservationRepository reservationRepository;

    public MyPurchaseServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<MyPurchaseHistoryResponse> getPurchaseHistoryByUserId(Long userId) {
        return reservationRepository.findPurchaseHistoryByUserId(userId, TossPaymentStatus.PAID);
    }
}

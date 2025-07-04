package com.goormitrip.goormitrip.mypage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.goormitrip.goormitrip.mypage.dto.MyPurchaseHistoryResponse;
import com.goormitrip.goormitrip.mypage.service.MyPurchaseService;
import com.goormitrip.goormitrip.reservation.repository.ReservationRepository;

@Service
public class MyPurchaseServiceImpl implements MyPurchaseService {

    private final ReservationRepository reservationRepository;

    public MyPurchaseServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<MyPurchaseHistoryResponse> getPurchaseHistoryByUserId(Long userId) {
        return reservationRepository.findPurchaseHistoryByUserId(userId);
    }
}

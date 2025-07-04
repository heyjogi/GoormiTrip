package com.goormitrip.goormitrip.mypage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.mypage.service.MyReservationService;
import com.goormitrip.goormitrip.mypage.dto.MyReservationResponse;
import com.goormitrip.goormitrip.reservation.repository.ReservationRepository;

@Service
public class MyReservationServiceImpl implements MyReservationService {

    private final ReservationRepository reservationRepository;

    public MyReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<MyReservationResponse> getReservationsByUserId(Long userId) {
        return reservationRepository.findReservationsWithProductAndPaymentByUserId(userId);
    }
}
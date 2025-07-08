package com.goormitrip.goormitrip.reservation.service;

import com.goormitrip.goormitrip.reservation.dto.MyReservationResponse;
import java.util.List;

public interface MyReservationService {
    List<MyReservationResponse> getReservationsByUserId(Long userId);
}

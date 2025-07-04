package com.goormitrip.goormitrip.mypage.service;

import com.goormitrip.goormitrip.mypage.dto.MyReservationResponse;
import java.util.List;

public interface MyReservationService {
    List<MyReservationResponse> getReservationsByUserId(Long userId);
}

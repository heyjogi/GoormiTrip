package com.goormitrip.goormitrip.product.service;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<LocalDate> getAvailableDates(Long productId);
}

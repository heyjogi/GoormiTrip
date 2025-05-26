package com.goormitrip.controller;

import com.goormitrip.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/available")
    public List<LocalDate> getAvailableDates(@RequestParam Long productId) {
        return reservationService.getAvailableDates(productId);
    }
}
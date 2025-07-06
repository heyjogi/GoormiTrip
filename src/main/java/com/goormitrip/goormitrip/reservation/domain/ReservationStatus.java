package com.goormitrip.goormitrip.reservation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ReservationStatus {
    RESERVED,
    CANCELLED,
    PENDING,
    COMPLETED
}

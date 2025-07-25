package com.goormitrip.goormitrip.reservation.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;
import com.goormitrip.goormitrip.product.domain.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.payment.domain.TossPayment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private TossPayment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private LocalDate travelDate;
    private int peopleCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}

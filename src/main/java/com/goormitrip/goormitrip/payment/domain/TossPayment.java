package com.goormitrip.goormitrip.payment.domain;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.reservation.domain.Reservation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {
	@Id
	private byte[] paymentId;

	@Column(nullable = false)
	private String tossPaymentKey;

	@Column(nullable = false)
	private String tossOrderId;

	private long totalAmount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TossPaymentMethod tossPaymentMethod;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TossPaymentStatus tossPaymentStatus;

	@Column(nullable = false)
	private LocalDateTime requestedAt;
	private LocalDateTime approvedAt;

	@Column(name = "reservation_id", insertable = false, updatable = false)
	private Long reservationId;

	@OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
	private Reservation reservation;

	private LocalDateTime refundedAt;
}
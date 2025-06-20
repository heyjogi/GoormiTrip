package com.goormitrip.goormitrip.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormitrip.goormitrip.payment.domain.TossPayment;

@Repository
public interface TossPaymentRepository extends JpaRepository<TossPayment, byte[]> {
	Optional<TossPayment> findByReservationId(Long reservationId);

	Optional<TossPayment> findByTossOrderId(String tossOrderId);
}

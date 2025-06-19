package com.goormitrip.goormitrip.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormitrip.goormitrip.payment.domain.TossPayment;

@Repository
public interface TossPaymentRepository extends JpaRepository<TossPayment, byte[]> {
}

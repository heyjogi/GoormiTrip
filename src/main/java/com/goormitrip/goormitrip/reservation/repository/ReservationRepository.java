package com.goormitrip.goormitrip.reservation.repository;

import com.goormitrip.goormitrip.reservation.domain.Reservation;
import com.goormitrip.goormitrip.reservation.domain.ReservationStatus;
import com.goormitrip.goormitrip.purchase.dto.MyPurchaseHistoryResponse;
import com.goormitrip.goormitrip.reservation.dto.MyReservationResponse;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByProductIdAndStatus(Long productId, ReservationStatus status);

    List<Reservation> findByProductIdAndTravelDate(Long productId, LocalDate travelDate);

    @Query("""
        SELECT new com.goormitrip.goormitrip.reservation.dto.MyReservationResponse(
            r.id, p.title, p.thumbnail, r.travelDate, r.peopleCount,
            r.status, pay.tossPaymentMethod, pay.tossPaymentStatus, pay.totalAmount, pay.approvedAt
            )
            FROM Reservation r
            JOIN r.product p
            LEFT JOIN r.payment pay
            WHERE r.user.id = :userId
            ORDER BY r.travelDate DESC
            """)
    List<MyReservationResponse> findReservationsWithProductAndPaymentByUserId(@Param("userId") Long userId);

    @Query("""
        SELECT new com.goormitrip.goormitrip.purchase.dto.MyPurchaseHistoryResponse(
                    p.title, p.thumbnail, pay.approvedAt, pay.totalAmount, pay.tossPaymentMethod, pay.tossPaymentStatus
                )
                FROM TossPayment pay
                JOIN pay.reservation r
                JOIN r.product p
                WHERE r.user.id = :userId AND pay.tossPaymentStatus = :status
                ORDER BY pay.approvedAt DESC
            """)
    List<MyPurchaseHistoryResponse> findPurchaseHistoryByUserId(@Param("userId") Long userId,
            @Param("status") TossPaymentStatus status);
}

package com.goormitrip.goormitrip.reservation.repository;

import com.goormitrip.goormitrip.reservation.domain.Reservation;
import com.goormitrip.goormitrip.reservation.domain.ReservationStatus;
import com.goormitrip.goormitrip.mypage.dto.MyPurchaseHistoryResponse;
import com.goormitrip.goormitrip.mypage.dto.MyReservationResponse;
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
            SELECT new com.goormitrip.goormitrip.mypage.dto.MyReservationResponse(
            r.id, p.title, p.thumbnailUrl, r.travelDate, r.peopleCount,
            r.status, pay.status, pay.method, pay.amount, pay.paidAt
            )
            FROM Reservation r
            JOIN Product p ON r.product.id = p.id
            LEFT JOIN Payment pay ON pay.reservation.id = r.id
            WHERE r.user.id = :userId
            ORDER BY r.travelDate DESC
            """)
    List<MyReservationResponse> findReservationsWithProductAndPaymentByUserId(@Param("userId") Long userId);

    @Query("""
                SELECT new com.goormitrip.goormitrip.mypage.dto.MyPurchaseHistoryResponse(
                    p.title, p.thumbnailUrl, pay.paidAt, pay.amount, pay.method, pay.status
                )
                FROM Payment pay
                JOIN Reservation r ON pay.reservation.id = r.id
                JOIN Product p ON r.product.id = p.id
                WHERE r.user.id = :userId AND pay.status = 'COMPLETED'
                ORDER BY pay.paidAt DESC
            """)
    List<MyPurchaseHistoryResponse> findPurchaseHistoryByUserId(@Param("userId") Long userId);
}

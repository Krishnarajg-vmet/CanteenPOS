package com.teamsynk.canteenpos.payment.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.payment.entity.Refund;
import com.teamsynk.canteenpos.common.enums.PaymentMode;

@Repository
public interface RefundRepository extends JpaRepository<Refund, UUID> {

    List<Refund> findByOrderId(UUID orderId);

    List<Refund> findByOrderIdOrderByRefundedAtDesc(UUID orderId);

    Page<Refund> findByOrderId(UUID orderId, Pageable pageable);

    List<Refund> findByPaymentId(UUID paymentId);

    Optional<Refund> findTopByPaymentIdOrderByRefundedAtDesc(UUID paymentId);

    List<Refund> findByRefundMode(PaymentMode refundMode);

    List<Refund> findByRefundModeAndRefundedAtBetween(
        PaymentMode refundMode,
        LocalDateTime from,
        LocalDateTime to
    );

    List<Refund> findByRefundedAtBetween(LocalDateTime from, LocalDateTime to);

    Page<Refund> findByRefundedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

    boolean existsByOrderId(UUID orderId);

    boolean existsByPaymentId(UUID paymentId);

    boolean existsByOrderIdAndRefundMode(UUID orderId, PaymentMode refundMode);

    @Query("SELECT COALESCE(SUM(r.refundAmount), 0) FROM Refund r WHERE r.order.id = :orderId")
    BigDecimal sumRefundAmountByOrderId(@Param("orderId") UUID orderId);

    @Query("SELECT COALESCE(SUM(r.refundAmount), 0) FROM Refund r WHERE r.payment.id = :paymentId")
    BigDecimal sumRefundAmountByPaymentId(@Param("paymentId") UUID paymentId);

    @Query("SELECT COALESCE(SUM(r.refundAmount), 0) FROM Refund r WHERE r.order.id IN :orderIds")
    BigDecimal sumRefundAmountByOrderIds(@Param("orderIds") List<UUID> orderIds);

    @Query("SELECT r FROM Refund r WHERE r.refundMode = :mode AND r.refundedAt BETWEEN :from AND :to")
    Page<Refund> findByModeAndDateRange(
        @Param("mode") PaymentMode mode,
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to,
        Pageable pageable
    );

    @Query("SELECT r FROM Refund r WHERE r.order.id = :orderId AND r.payment.id = :paymentId")
    List<Refund> findByOrderIdAndPaymentId(
        @Param("orderId") UUID orderId,
        @Param("paymentId") UUID paymentId
    );

	Optional<Refund> findByOrderIdAndRefundReason(UUID orderId, String reason);
}

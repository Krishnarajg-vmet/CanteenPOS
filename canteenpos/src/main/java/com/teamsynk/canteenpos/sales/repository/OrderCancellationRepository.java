package com.teamsynk.canteenpos.sales.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.sales.entity.OrderCancellation;

@Repository
public interface OrderCancellationRepository extends JpaRepository<OrderCancellation, UUID> {

    List<OrderCancellation> findByOrderId(UUID orderId);

    Optional<OrderCancellation> findByOrderIdAndId(UUID orderId, UUID cancellationId);

    List<OrderCancellation> findByCancelledBy(String cancelledBy);

    List<OrderCancellation> findByCancelledAtBetween(LocalDateTime start, LocalDateTime end);

    List<OrderCancellation> findByOrderIdAndCancelledAtBetween(UUID orderId, LocalDateTime start, LocalDateTime end);

    List<OrderCancellation> findByReasonContainingIgnoreCase(String reason);

    List<OrderCancellation> findByOrderIdIn(List<UUID> orderIds);

    Optional<OrderCancellation> findTopByOrderIdOrderByCancelledAtDesc(UUID orderId);
}

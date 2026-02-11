package com.teamsynk.canteenpos.sales.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.common.enums.OrderItemStatus;
import com.teamsynk.canteenpos.sales.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    List<OrderItem> findByOrderId(UUID orderId);
    List<OrderItem> findByOrderIdAndStatus(UUID orderId, OrderItemStatus status);
    List<OrderItem> findByOrderIdAndStatusIn(UUID orderId, List<OrderItemStatus> statuses);

    Optional<OrderItem> findByOrderIdAndFoodId(UUID orderId, UUID foodId);
    List<OrderItem> findByFoodId(UUID foodId);
    List<OrderItem> findByFoodIdAndStatus(UUID foodId, OrderItemStatus status);

    List<OrderItem> findByOrderIdIn(List<UUID> orderIds);

    List<OrderItem> findByStatus(OrderItemStatus status);

    @Query("SELECT SUM(oi.totalPrice) FROM OrderItem oi WHERE oi.order.id = :orderId AND oi.status = 'ACTIVE'")
    BigDecimal sumActiveItemTotalByOrderId(UUID orderId);
}

package com.teamsynk.canteenpos.sales.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.common.enums.OrderStatus;
import com.teamsynk.canteenpos.sales.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findBySalesStatus(OrderStatus status, Pageable pageable);

    List<Order> findBySalesStatusIn(List<OrderStatus> statuses);

    boolean existsByOrderCode(String orderCode);

    Optional<Order> findByCompanyIdAndBranchIdAndOrderCode(UUID companyId, UUID branchId, String orderCode);

    Page<Order> findByCompanyIdAndBranchIdAndSalesStatus(
        UUID companyId, UUID branchId, OrderStatus status, Pageable pageable);

    List<Order> findAllByCompanyIdAndBranchIdAndOrderDateAfterOrderByOrderDateDesc(
        UUID companyId, UUID branchId, LocalDateTime fromDate);

    List<Order> findByCompanyIdAndBranchIdAndSalesStatusAndOrderDateBetween(
        UUID companyId, UUID branchId, OrderStatus status, LocalDateTime start, LocalDateTime end);

    List<Order> findByBranchIdAndOrderDateBetween(UUID branchId, LocalDateTime start, LocalDateTime end);

    Page<Order> findByBranchIdAndOrderDateBetween(UUID branchId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    List<Order> findByIdIn(List<UUID> orderIds);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = :orderId")
    Optional<Order> findByIdWithItems(UUID orderId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.companyId = :companyId AND o.branchId = :branchId AND o.orderDate BETWEEN :start AND :end")
    List<Order> findAllWithItemsByCompanyAndBranchAndDateRange(UUID companyId, UUID branchId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.branchId = :branchId AND o.orderDate BETWEEN :start AND :end")
    long countOrdersForBranch(UUID branchId, LocalDateTime start, LocalDateTime end);

	List<Order> findByBranchId(UUID branchId);
}

package com.teamsynk.canteenpos.sales.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teamsynk.canteenpos.sales.dto.request.OrderRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto request, UUID companyId, UUID branchId);

    OrderResponseDto getOrderById(UUID orderId);

    List<OrderResponseDto> getOrdersByBranch(UUID branchId);

    Page<OrderResponseDto> getOrdersPaginated(UUID companyId, UUID branchId, Pageable pageable);

    void cancelOrderFully(UUID orderId, String cancelledBy, String reason);

    void cancelOrderItems(
            UUID orderId,
            List<UUID> orderItemIds,
            String cancelledBy,
            String reason
    );
}

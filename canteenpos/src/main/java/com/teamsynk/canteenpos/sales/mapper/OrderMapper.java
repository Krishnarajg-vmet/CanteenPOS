package com.teamsynk.canteenpos.sales.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.sales.dto.request.OrderRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderCancellationResponseDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderItemResponseDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderResponseDto;
import com.teamsynk.canteenpos.sales.entity.Order;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDto dto) {
        if (dto == null) return null;

        Order order = new Order();
        order.setCompanyId(dto.getCompanyId());
        order.setBranchId(dto.getBranchId());

        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            dto.getItems().stream()
                    .map(OrderItemMapper::toEntity)
                    .forEach(order::addItem);
        }

        return order;
    }

    public OrderResponseDto toDto(Order order) {
        if (order == null) return null;

        List<OrderItemResponseDto> items = order.getOrderItems() == null ? List.of() :
                order.getOrderItems().stream()
                     .map(OrderItemMapper::toDto)
                     .collect(Collectors.toUnmodifiableList());

        List<OrderCancellationResponseDto> cancellations = List.of(); // Add actual mapping if needed

        BigDecimal grossAmount = order.getOrderItems() == null ? BigDecimal.ZERO :
                order.getOrderItems().stream()
                     .map(i -> i.getTotalPrice() == null ? BigDecimal.ZERO : i.getTotalPrice())
                     .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        BigDecimal netAmount = grossAmount.subtract(discountAmount).add(taxAmount);

        return new OrderResponseDto(
                order.getId(),
                order.getOrderCode(),
                order.getCompanyId(),
                order.getBranchId(),
                order.getSalesStatus(),
                order.getOrderDate(),
                grossAmount,
                discountAmount,
                taxAmount,
                netAmount,
                items,
                cancellations
        );
    }
}

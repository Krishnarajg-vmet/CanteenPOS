package com.teamsynk.canteenpos.sales.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.sales.dto.request.OrderCancellationRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderCancellationResponseDto;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.entity.OrderCancellation;

@Component
public class OrderCancellationMapper {

    public static OrderCancellation toEntity(Order order, OrderCancellationRequestDto dto) {
        if (order == null) {
            throw new IllegalArgumentException("Order entity cannot be null");
        }
        if (dto == null) return null;

        return OrderCancellation.cancelOrder(order, dto.getCancelledBy(), dto.getReason());
    }

    public static OrderCancellationResponseDto toDto(OrderCancellation entity) {
        if (entity == null) return null;

        return new OrderCancellationResponseDto(
                entity.getId(),
                entity.getOrder() != null ? entity.getOrder().getId() : null,
                entity.getCancelledBy(),
                entity.getReason(),
                entity.getCancelledAt()
        );
    }

    public static java.util.List<OrderCancellationResponseDto> toDtoList(java.util.List<OrderCancellation> entities) {
        if (entities == null || entities.isEmpty()) return java.util.List.of();

        return entities.stream()
                       .filter(Objects::nonNull)
                       .map(OrderCancellationMapper::toDto)
                       .collect(Collectors.toUnmodifiableList());
    }
}

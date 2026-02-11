package com.teamsynk.canteenpos.sales.mapper;

import com.teamsynk.canteenpos.sales.dto.request.OrderItemRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderItemResponseDto;
import com.teamsynk.canteenpos.sales.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItem toEntity(OrderItemRequestDto dto) {
        if (dto == null) return null;
        return new OrderItem(
                dto.getFoodId(),
                dto.getUomId(),
                dto.getQuantity(),
                dto.getUnitPrice()
        );
    }

    public static OrderItemResponseDto toDto(OrderItem entity) {
        if (entity == null) return null;
        return new OrderItemResponseDto(
                entity.getId(),
                entity.getFoodId(),
                entity.getUomId(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                entity.getTotalPrice(),
                entity.getStatus().name()
        );
    }
}

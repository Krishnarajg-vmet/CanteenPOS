package com.teamsynk.canteenpos.sales.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemResponseDto {

    private final UUID orderItemId;
    private final UUID foodId;
    private final UUID uomId;
    private final BigDecimal quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal totalPrice;
    private final String status;

    public OrderItemResponseDto(UUID orderItemId, UUID foodId, UUID uomId,
                                BigDecimal quantity, BigDecimal unitPrice, 
                                BigDecimal totalPrice, String status) {
        this.orderItemId = orderItemId;
        this.foodId = foodId;
        this.uomId = uomId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public UUID getOrderItemId() {
        return orderItemId;
    }

    public UUID getFoodId() {
        return foodId;
    }

    public UUID getUomId() {
        return uomId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }
}

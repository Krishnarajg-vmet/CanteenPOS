package com.teamsynk.canteenpos.sales.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemRequestDto {

    private UUID foodId;
    private UUID uomId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

    public OrderItemRequestDto() {}

    public OrderItemRequestDto(UUID foodId, UUID uomId, BigDecimal quantity, BigDecimal unitPrice) {
        this.foodId = foodId;
        this.uomId = uomId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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
}

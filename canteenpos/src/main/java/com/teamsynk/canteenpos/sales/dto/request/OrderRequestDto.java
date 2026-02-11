package com.teamsynk.canteenpos.sales.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderRequestDto {

    private UUID companyId;
    private UUID branchId;
    private BigDecimal discountAmount;
    private List<OrderItemRequestDto> items;

    public OrderRequestDto() {}

    public OrderRequestDto(UUID companyId, UUID branchId, BigDecimal discountAmount, List<OrderItemRequestDto> items) {
        this.companyId = companyId;
        this.branchId = branchId;
        this.discountAmount = discountAmount;
        this.items = List.copyOf(items);
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public UUID getBranchId() {
        return branchId;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public List<OrderItemRequestDto> getItems() {
        return items;
    }
}

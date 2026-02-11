package com.teamsynk.canteenpos.sales.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.OrderStatus;

public class OrderResponseDto {

    private final UUID orderId;
    private final String orderCode;
    private final UUID companyId;
    private final UUID branchId;
    private final OrderStatus salesStatus;
    private final LocalDateTime orderDate;

    private final BigDecimal grossAmount;
    private final BigDecimal discountAmount;
    private final BigDecimal taxAmount;
    private final BigDecimal netAmount;

    private final List<OrderItemResponseDto> items;
    private final List<OrderCancellationResponseDto> cancellations;

    public OrderResponseDto(UUID orderId, String orderCode, UUID companyId, UUID branchId, 
                            OrderStatus salesStatus, LocalDateTime orderDate,
                            BigDecimal grossAmount, BigDecimal discountAmount, 
                            BigDecimal taxAmount, BigDecimal netAmount, 
                            List<OrderItemResponseDto> items,
                            List<OrderCancellationResponseDto> cancellations) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.companyId = companyId;
        this.branchId = branchId;
        this.salesStatus = salesStatus;
        this.orderDate = orderDate;
        this.grossAmount = grossAmount;
        this.discountAmount = discountAmount;
        this.taxAmount = taxAmount;
        this.netAmount = netAmount;
        this.items = items == null ? List.of() : List.copyOf(items);
        this.cancellations = cancellations == null ? List.of() : List.copyOf(cancellations);
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public UUID getBranchId() {
        return branchId;
    }

    public OrderStatus getSalesStatus() {
        return salesStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public List<OrderItemResponseDto> getItems() {
        return items;
    }

    public List<OrderCancellationResponseDto> getCancellations() {
        return cancellations;
    }
}

package com.teamsynk.canteenpos.sales.dto.request;

import java.util.UUID;

public class OrderCancellationRequestDto {

    private UUID orderId;
    private String cancelledBy;
    private String reason;

    public OrderCancellationRequestDto() {}

    public OrderCancellationRequestDto(UUID orderId, String cancelledBy, String reason) {
        this.orderId = orderId;
        this.cancelledBy = cancelledBy;
        this.reason = reason;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public String getReason() {
        return reason;
    }
}

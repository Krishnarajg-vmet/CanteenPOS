package com.teamsynk.canteenpos.sales.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderCancellationResponseDto {

    private final UUID cancellationId;
    private final UUID orderId;
    private final String cancelledBy;
    private final String reason;
    private final LocalDateTime cancelledAt;

    public OrderCancellationResponseDto(UUID cancellationId, UUID orderId,
                                        String cancelledBy, String reason,
                                        LocalDateTime cancelledAt) {
        this.cancellationId = cancellationId;
        this.orderId = orderId;
        this.cancelledBy = cancelledBy;
        this.reason = reason;
        this.cancelledAt = cancelledAt;
    }

    public UUID getCancellationId() {
        return cancellationId;
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

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
}

package com.teamsynk.canteenpos.sales.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FinancialSnapshotResponseDto {

    private final UUID snapshotId;
    private final UUID orderId;

    private final BigDecimal grossAmount;
    private final BigDecimal activeItemAmount;
    private final BigDecimal totalPaid;
    private final BigDecimal totalRefunded;
    private final BigDecimal netPayable;

    private final LocalDateTime capturedAt;

    public FinancialSnapshotResponseDto(
            UUID snapshotId,
            UUID orderId,
            BigDecimal grossAmount,
            BigDecimal activeItemAmount,
            BigDecimal totalPaid,
            BigDecimal totalRefunded,
            BigDecimal netPayable,
            LocalDateTime capturedAt
    ) {
        this.snapshotId = snapshotId;
        this.orderId = orderId;
        this.grossAmount = grossAmount;
        this.activeItemAmount = activeItemAmount;
        this.totalPaid = totalPaid;
        this.totalRefunded = totalRefunded;
        this.netPayable = netPayable;
        this.capturedAt = capturedAt;
    }

	public UUID getSnapshotId() {
		return snapshotId;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public BigDecimal getActiveItemAmount() {
		return activeItemAmount;
	}

	public BigDecimal getTotalPaid() {
		return totalPaid;
	}

	public BigDecimal getTotalRefunded() {
		return totalRefunded;
	}

	public BigDecimal getNetPayable() {
		return netPayable;
	}

	public LocalDateTime getCapturedAt() {
		return capturedAt;
	}

    
}

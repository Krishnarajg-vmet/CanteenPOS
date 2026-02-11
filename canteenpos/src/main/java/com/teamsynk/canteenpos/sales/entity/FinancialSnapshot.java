package com.teamsynk.canteenpos.sales.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "order_financial_snapshots",
    indexes = {
        @Index(name = "idx_snapshot_order", columnList = "order_id"),
        @Index(name = "idx_snapshot_time", columnList = "captured_at")
    }
)
public class FinancialSnapshot extends BaseEntity {

    @Id
    @Column(name = "snapshot_id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "gross_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal grossAmount;

    @Column(name = "active_item_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal activeItemAmount;

    @Column(name = "total_paid", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalPaid;

    @Column(name = "total_refunded", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalRefunded;

    @Column(name = "net_payable", nullable = false, precision = 18, scale = 2)
    private BigDecimal netPayable;

    @Column(name = "captured_at", nullable = false)
    private LocalDateTime capturedAt;

    @PrePersist
    private void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (capturedAt == null) capturedAt = LocalDateTime.now();
    }

    public static FinancialSnapshot capture(
            Order order,
            BigDecimal grossAmount,
            BigDecimal activeItemAmount,
            BigDecimal totalPaid,
            BigDecimal totalRefunded
    ) {

        FinancialSnapshot snapshot = new FinancialSnapshot();
        snapshot.order = order;
        snapshot.grossAmount = grossAmount;
        snapshot.activeItemAmount = activeItemAmount;
        snapshot.totalPaid = totalPaid;
        snapshot.totalRefunded = totalRefunded;

        snapshot.netPayable = totalPaid
                .subtract(totalRefunded)
                .subtract(activeItemAmount);

        return snapshot;
    }

	public UUID getId() {
		return id;
	}

	public Order getOrder() {
		return order;
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

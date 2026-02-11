package com.teamsynk.canteenpos.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.PaymentMode;
import com.teamsynk.canteenpos.common.enums.RefundStatus;
import com.teamsynk.canteenpos.sales.entity.Order;

import jakarta.persistence.*;

@Entity
@Table(
    name = "refunds",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_refund_order_reason",
            columnNames = {"order_id", "refund_reason"}
        )
    },
    indexes = {
        @Index(name = "idx_refund_order", columnList = "order_id"),
        @Index(name = "idx_refund_payment", columnList = "payment_id")
    }
)
public class Refund extends BaseEntity {

    @Id
    @Column(name = "refund_id", nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private com.teamsynk.canteenpos.payment.entity.Payment payment;

    @Column(name = "refund_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal refundAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "refund_mode", nullable = false)
    private PaymentMode refundMode;

    @Column(name = "refund_reason", length = 500)
    private String refundReason;

    @Column(name = "refunded_at", nullable = false)
    private LocalDateTime refundedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "refund_status", nullable = false)
    private RefundStatus refundStatus;

    @PrePersist
    private void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (refundedAt == null) refundedAt = LocalDateTime.now();
    }

    public static Refund createRefund(
            Order order,
            Payment payment,
            BigDecimal amount,
            PaymentMode mode,
            String reason
    ) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Refund amount must be positive");
        if (mode == null) throw new IllegalArgumentException("Refund mode cannot be null");

        Refund r = new Refund();
        r.order = order;
        r.payment = payment;
        r.refundAmount = amount;
        r.refundMode = mode;
        r.refundReason = reason;
        r.refundStatus = RefundStatus.INITIATED;
        r.refundedAt = LocalDateTime.now();
        return r;
    }

    public void markCompleted() {
        this.refundStatus = RefundStatus.COMPLETED;
    }

    public void markFailed() {
        this.refundStatus = RefundStatus.FAILED;
    }

    public UUID getId() { return id; }
    public Long getVersion() { return version; }
    public Order getOrder() { return order; }
    public com.teamsynk.canteenpos.payment.entity.Payment getPayment() { return payment; }
    public BigDecimal getRefundAmount() { return refundAmount; }
    public PaymentMode getRefundMode() { return refundMode; }
    public String getRefundReason() { return refundReason; }
    public LocalDateTime getRefundedAt() { return refundedAt; }

    public RefundStatus getRefundStatus() {
		return refundStatus;
	}

	private void setOrder(Order order) { this.order = order; }
    private void setPayment(com.teamsynk.canteenpos.payment.entity.Payment payment) { this.payment = payment; }
    private void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }
    private void setRefundMode(PaymentMode refundMode) { this.refundMode = refundMode; }
    private void setRefundReason(String refundReason) { this.refundReason = refundReason; }
    private void setRefundedAt(LocalDateTime refundedAt) { this.refundedAt = refundedAt; }
    private void setRefundStatus(RefundStatus refundStatus) { this.refundStatus = refundStatus; }
}

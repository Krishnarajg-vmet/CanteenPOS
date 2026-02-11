package com.teamsynk.canteenpos.sales.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_cancellations")
public class OrderCancellation extends BaseEntity {

    @Id
    @Column(name = "order_cancellation_id", nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "cancelled_by", nullable = false)
    private String cancelledBy;

    @Column(name = "cancelled_at", nullable = false)
    private LocalDateTime cancelledAt;

    @Column(name = "reason", length = 500)
    private String reason;

    @PrePersist
    private void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (cancelledAt == null) cancelledAt = LocalDateTime.now();
    }

    public static OrderCancellation cancelOrder(Order order, String cancelledBy, String reason) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        OrderCancellation oc = new OrderCancellation();
        oc.order = order;
        oc.cancelledBy = cancelledBy;
        oc.reason = reason;
        oc.cancelledAt = LocalDateTime.now();
        return oc;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public Order getOrder() {
        return order;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public String getReason() {
        return reason;
    }

    private void setOrder(Order order) {
        this.order = order;
    }

    private void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    private void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    private void setReason(String reason) {
        this.reason = reason;
    }
}

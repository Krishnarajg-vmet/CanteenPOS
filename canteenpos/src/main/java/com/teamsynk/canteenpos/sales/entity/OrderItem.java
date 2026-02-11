package com.teamsynk.canteenpos.sales.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.OrderItemStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @Id
    @Column(name = "order_item_id", nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "food_id", nullable = false)
    private UUID foodId;

    @Column(name = "uom_id", nullable = false)
    private UUID uomId;

    @Column(name = "quantity", nullable = false, precision = 18, scale = 3)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderItemStatus status;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @PrePersist
    private void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (status == null) status = OrderItemStatus.ACTIVE;
        recalculateTotal();
    }

    @PreUpdate
    private void preUpdate() {
        if (status == OrderItemStatus.CANCELLED) {
            return;
        }
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.totalPrice = unitPrice.multiply(quantity);
    }

    public void cancel(String reason) {
        if (this.status == OrderItemStatus.CANCELLED) {
            throw new IllegalStateException("Order item already cancelled");
        }
        this.status = OrderItemStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
        this.cancelReason = reason;
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

    public OrderItemStatus getStatus() {
        return status;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    void setOrder(Order order) {
        this.order = order;
    }

    public void updateQuantity(BigDecimal quantity) {
        if (status == OrderItemStatus.CANCELLED) {
            throw new IllegalStateException("Cannot modify cancelled item");
        }
        this.quantity = quantity;
    }

    public void updateUnitPrice(BigDecimal unitPrice) {
        if (status == OrderItemStatus.CANCELLED) {
            throw new IllegalStateException("Cannot modify cancelled item");
        }
        this.unitPrice = unitPrice;
    }

    public OrderItem(UUID foodId, UUID uomId, BigDecimal quantity, BigDecimal unitPrice) {
        this.foodId = foodId;
        this.uomId = uomId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = OrderItemStatus.ACTIVE;
        recalculateTotal();
    }

    
}

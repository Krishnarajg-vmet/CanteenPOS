package com.teamsynk.canteenpos.sales.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.OrderItemStatus;
import com.teamsynk.canteenpos.common.enums.OrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "order_code", nullable = false, unique = true)
    private String orderCode;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_status", nullable = false)
    private OrderStatus salesStatus;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "branch_id", nullable = false)
    private UUID branchId;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (orderDate == null) orderDate = LocalDateTime.now();
        if (salesStatus == null) salesStatus = OrderStatus.CREATED;
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        this.orderItems.add(item);
    }

    public void removeItem(OrderItem item) {
        this.orderItems.remove(item);
        item.setOrder(null);
    }
    
    public void cancelFully(String cancelledBy, String reason) {

        if (this.salesStatus == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order already cancelled");
        }

        this.orderItems.stream()
            .filter(i -> i.getStatus() == OrderItemStatus.ACTIVE)
            .forEach(i -> i.cancel(reason));

        this.salesStatus = OrderStatus.CANCELLED;
    }
    
    public void cancelItems(
            List<UUID> orderItemIds,
            String cancelledBy,
            String reason
    ) {
        if (orderItemIds == null || orderItemIds.isEmpty()) {
            throw new IllegalArgumentException("No items selected for cancellation");
        }

        boolean anyCancelled = false;

        for (OrderItem item : this.orderItems) {
            if (orderItemIds.contains(item.getId())
                    && item.getStatus() == OrderItemStatus.ACTIVE) {
                item.cancel(reason);
                anyCancelled = true;
            }
        }

        if (!anyCancelled) {
            throw new IllegalStateException("No active items were cancelled");
        }
    }



    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getSalesStatus() {
        return salesStatus;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public UUID getBranchId() {
        return branchId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setSalesStatus(OrderStatus salesStatus) {
        this.salesStatus = salesStatus;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }
}

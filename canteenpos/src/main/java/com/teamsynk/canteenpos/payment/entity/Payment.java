package com.teamsynk.canteenpos.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.PaymentMode;
import com.teamsynk.canteenpos.common.enums.PaymentStatus;
import com.teamsynk.canteenpos.sales.entity.Order;

import jakarta.persistence.*;

@Entity
@Table(name = "payments",
       indexes = {
           @Index(name = "idx_payment_order", columnList = "order_id"),
           @Index(name = "idx_payment_status", columnList = "payment_status")
       })
public class Payment extends BaseEntity {

    @Id
    @Column(name = "payment_id", nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "branch_id", nullable = false)
    private UUID branchId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_ref")
    private String transactionRef;

    @Column(name = "payment_time", nullable = false)
    private LocalDateTime paymentTime;

    @PrePersist
    private void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (paymentStatus == null) paymentStatus = PaymentStatus.INITIATED;
        if (paymentTime == null) paymentTime = LocalDateTime.now();
    }

    public static Payment createPayment(Order order,
                                        BigDecimal amount,
                                        PaymentMode mode,
                                        String transactionRef) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Payment amount must be positive");

        Payment p = new Payment();
        p.order = order;
        p.companyId = order.getCompanyId();
        p.branchId = order.getBranchId();
        p.amount = amount;
        p.paymentMode = mode;
        p.transactionRef = transactionRef;
        p.paymentStatus = PaymentStatus.INITIATED;
        p.paymentTime = LocalDateTime.now();
        return p;
    }

    public void markAsCompleted() {
        if (paymentStatus != PaymentStatus.INITIATED)
            throw new IllegalStateException("Only initiated payments can be completed");
        this.paymentStatus = PaymentStatus.COMPLETED;
    }

    public void markAsFailed() {
        if (paymentStatus != PaymentStatus.INITIATED)
            throw new IllegalStateException("Only initiated payments can fail");
        this.paymentStatus = PaymentStatus.FAILED;
    }

    public UUID getId() { return id; }
    public Long getVersion() { return version; }
    public Order getOrder() { return order; }
    public UUID getCompanyId() { return companyId; }
    public UUID getBranchId() { return branchId; }
    public PaymentMode getPaymentMode() { return paymentMode; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public BigDecimal getAmount() { return amount; }
    public String getTransactionRef() { return transactionRef; }
    public LocalDateTime getPaymentTime() { return paymentTime; }

    private void setOrder(Order order) { this.order = order; }
    private void setAmount(BigDecimal amount) { this.amount = amount; }
    private void setPaymentMode(PaymentMode paymentMode) { this.paymentMode = paymentMode; }
    private void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    private void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }
}

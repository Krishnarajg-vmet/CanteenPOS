package com.teamsynk.canteenpos.payment.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.PaymentMode;
import com.teamsynk.canteenpos.common.enums.PaymentStatus;

public class PaymentResponseDto {

    private final UUID paymentId;
    private final UUID orderId;
    private final UUID companyId;
    private final UUID branchId;

    private final PaymentMode paymentMode;
    private final PaymentStatus paymentStatus;

    private final BigDecimal amount;
    private final String transactionRef;
    private final LocalDateTime paymentTime;

    public PaymentResponseDto(
            UUID paymentId,
            UUID orderId,
            UUID companyId,
            UUID branchId,
            PaymentMode paymentMode,
            PaymentStatus paymentStatus,
            BigDecimal amount,
            String transactionRef,
            LocalDateTime paymentTime
    ) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.companyId = companyId;
        this.branchId = branchId;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.transactionRef = transactionRef;
        this.paymentTime = paymentTime;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public UUID getBranchId() {
        return branchId;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
}

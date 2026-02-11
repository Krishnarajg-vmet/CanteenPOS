package com.teamsynk.canteenpos.payment.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.PaymentMode;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentRequestDto {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID companyId;

    @NotNull
    private UUID branchId;

    @NotNull
    private PaymentMode paymentMode;

    @NotNull
    @Positive
    private BigDecimal amount;

    private String transactionRef;

    public PaymentRequestDto() {}

    public PaymentRequestDto(
            UUID orderId,
            UUID companyId,
            UUID branchId,
            PaymentMode paymentMode,
            BigDecimal amount,
            String transactionRef
    ) {
        this.orderId = orderId;
        this.companyId = companyId;
        this.branchId = branchId;
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.transactionRef = transactionRef;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionRef() {
        return transactionRef;
    }
}

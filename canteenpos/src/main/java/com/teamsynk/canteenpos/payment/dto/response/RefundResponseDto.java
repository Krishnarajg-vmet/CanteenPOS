package com.teamsynk.canteenpos.payment.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public class RefundResponseDto {
    private UUID refundId;
    private UUID orderId;
    private UUID paymentId;
    private BigDecimal refundAmount;
    private String refundMode;
    private String refundReason;
    
	public RefundResponseDto() {
	}

	public RefundResponseDto(UUID refundId, UUID orderId, UUID paymentId, BigDecimal refundAmount, String refundMode,
			String refundReason) {
		this.refundId = refundId;
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.refundAmount = refundAmount;
		this.refundMode = refundMode;
		this.refundReason = refundReason;
	}

	public UUID getRefundId() {
		return refundId;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public UUID getPaymentId() {
		return paymentId;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public String getRefundMode() {
		return refundMode;
	}

	public String getRefundReason() {
		return refundReason;
	}
    
}

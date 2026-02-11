package com.teamsynk.canteenpos.payment.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class RefundRequestDto {
    private UUID orderId;
    private UUID paymentId;
    private BigDecimal refundAmount;
    private String refundMode;
    private String reason;
    
	public RefundRequestDto() {
	}

	public RefundRequestDto(UUID orderId, UUID paymentId, BigDecimal refundAmount, String refundMode, String reason) {
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.refundAmount = refundAmount;
		this.refundMode = refundMode;
		this.reason = reason;
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

	public String getReason() {
		return reason;
	}    
}


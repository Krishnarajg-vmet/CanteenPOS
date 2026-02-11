package com.teamsynk.canteenpos.payment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.teamsynk.canteenpos.payment.entity.Refund;

public interface RefundService {

    Refund processRefundForOrder(UUID orderId, String reason);

    Refund processRefundForPayment(UUID orderId, UUID paymentId, String reason);

    BigDecimal getTotalRefundedAmount(UUID orderId);

    List<Refund> getRefundsForOrder(UUID orderId);
}

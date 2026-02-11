package com.teamsynk.canteenpos.payment.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentAggregationService {

    BigDecimal getTotalCompletedPaid(UUID orderId);

    BigDecimal getTotalInitiatedPaid(UUID orderId);

    BigDecimal getRefundablePaid(UUID orderId);
}

package com.teamsynk.canteenpos.payment.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.enums.PaymentStatus;
import com.teamsynk.canteenpos.payment.repository.PaymentRepository;

@Service
@Transactional(readOnly = true)
public class DefaultPaymentAggregationService implements PaymentAggregationService {

    private final PaymentRepository paymentRepository;

    public DefaultPaymentAggregationService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public BigDecimal getTotalCompletedPaid(UUID orderId) {
        return paymentRepository
                .sumAmountByOrderIdAndPaymentStatus(orderId, PaymentStatus.COMPLETED)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTotalInitiatedPaid(UUID orderId) {
        return paymentRepository
                .sumAmountByOrderIdAndPaymentStatus(orderId, PaymentStatus.INITIATED)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getRefundablePaid(UUID orderId) {
        // Only COMPLETED payments are refundable
        return getTotalCompletedPaid(orderId);
    }
}

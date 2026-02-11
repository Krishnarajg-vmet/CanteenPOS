package com.teamsynk.canteenpos.payment.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teamsynk.canteenpos.common.enums.PaymentStatus;
import com.teamsynk.canteenpos.payment.entity.Payment;
import com.teamsynk.canteenpos.payment.repository.PaymentRepository;

@Service
public class OrderPaymentConsistencyService {

    private final PaymentRepository paymentRepository;

    public OrderPaymentConsistencyService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public BigDecimal calculateTotalPaid(UUID orderId, UUID companyId, UUID branchId) {
        return paymentRepository
                .findByOrderIdAndCompanyIdAndBranchIdOrderByPaymentTimeAsc(
                        orderId, companyId, branchId)
                .stream()
                .filter(p -> p.getPaymentStatus() == PaymentStatus.COMPLETED)
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isOrderFullyPaid(
            UUID orderId,
            UUID companyId,
            UUID branchId,
            BigDecimal orderNetAmount) {

        return calculateTotalPaid(orderId, companyId, branchId)
                .compareTo(orderNetAmount) >= 0;
    }
}


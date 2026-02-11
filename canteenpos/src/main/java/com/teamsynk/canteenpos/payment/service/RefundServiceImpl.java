package com.teamsynk.canteenpos.payment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.enums.PaymentMode;
import com.teamsynk.canteenpos.common.enums.PaymentStatus;
import com.teamsynk.canteenpos.common.enums.RefundStatus;
import com.teamsynk.canteenpos.payment.entity.Payment;
import com.teamsynk.canteenpos.payment.entity.Refund;
import com.teamsynk.canteenpos.payment.repository.PaymentRepository;
import com.teamsynk.canteenpos.payment.repository.RefundRepository;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.financial.OrderFinancialCalculator;
import com.teamsynk.canteenpos.sales.repository.OrderRepository;

@Service
@Transactional
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final OrderFinancialCalculator financialCalculator;

    public RefundServiceImpl(
            RefundRepository refundRepository,
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            OrderFinancialCalculator financialCalculator
    ) {
        this.refundRepository = refundRepository;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.financialCalculator = financialCalculator;
    }

    @Override
    @Transactional
    public Refund processRefundForOrder(UUID orderId, String reason) {

        Refund existing = refundRepository.findByOrderIdAndRefundReason(orderId, reason)
                .orElse(null);

        if (existing != null) {
            switch (existing.getRefundStatus()) {
                case COMPLETED:
                    return existing;
                case INITIATED:
                case FAILED:
                    break;
            }
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        BigDecimal cancelledValue = financialCalculator.calculateCancelledItemAmount(order);
        BigDecimal alreadyRefunded = refundRepository.sumRefundAmountByOrderId(orderId);
        BigDecimal refundable = cancelledValue.subtract(alreadyRefunded);

        if (refundable.compareTo(BigDecimal.ZERO) <= 0) {
            return existing;
        }

        Refund refund = existing != null
                ? existing
                : Refund.createRefund(order, null, refundable, PaymentMode.CASH, reason);

        if (existing == null) {
            refundRepository.save(refund);
        }

        try {
            refund.markCompleted();

        } catch (Exception ex) {
            refund.markFailed();
            refundRepository.save(refund);
            throw ex;
        }

        return refundRepository.save(refund);
    }

    @Override
    @Transactional
    public Refund processRefundForPayment(UUID orderId, UUID paymentId, String reason) {

        Refund existing = refundRepository.findByOrderIdAndRefundReason(orderId, reason)
                .orElse(null);

        if (existing != null) {
            if (existing.getRefundStatus() == RefundStatus.COMPLETED) {
                return existing;
            }
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        if (payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new IllegalStateException("Only completed payments can be refunded");
        }

        BigDecimal paid = payment.getAmount();
        BigDecimal refunded = refundRepository.sumRefundAmountByPaymentId(paymentId);
        BigDecimal refundable = paid.subtract(refunded);

        if (refundable.compareTo(BigDecimal.ZERO) <= 0) {
            return existing; // already fully refunded
        }

        Refund refund = Refund.createRefund(order, payment, refundable, payment.getPaymentMode(), reason);
        refundRepository.save(refund);

        try {
            refund.markCompleted();
        } catch (Exception ex) {
            refund.markFailed();
            refundRepository.save(refund);
            throw ex;
        }

        return refundRepository.save(refund);
    }

    @Override
    public BigDecimal getTotalRefundedAmount(UUID orderId) {
        return refundRepository.sumRefundAmountByOrderId(orderId);
    }

    @Override
    public List<Refund> getRefundsForOrder(UUID orderId) {
        return refundRepository.findByOrderIdOrderByRefundedAtDesc(orderId);
    }
}

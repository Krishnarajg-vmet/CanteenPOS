package com.teamsynk.canteenpos.sales.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.enums.PaymentStatus;
import com.teamsynk.canteenpos.payment.repository.PaymentRepository;
import com.teamsynk.canteenpos.payment.repository.RefundRepository;
import com.teamsynk.canteenpos.sales.entity.FinancialSnapshot;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.financial.OrderFinancialCalculator;
import com.teamsynk.canteenpos.sales.repository.FinancialSnapshotRepository;
import com.teamsynk.canteenpos.sales.repository.OrderRepository;

@Service
@Transactional
public class FinancialSnapshotServiceImpl implements FinancialSnapshotService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;
    private final OrderFinancialCalculator financialCalculator;
    private final FinancialSnapshotRepository snapshotRepository;

    public FinancialSnapshotServiceImpl(
            OrderRepository orderRepository,
            PaymentRepository paymentRepository,
            RefundRepository refundRepository,
            OrderFinancialCalculator financialCalculator,
            FinancialSnapshotRepository snapshotRepository
    ) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.refundRepository = refundRepository;
        this.financialCalculator = financialCalculator;
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    public FinancialSnapshot captureSnapshot(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        BigDecimal gross = financialCalculator.calculateGrossAmount(order);
        BigDecimal active = financialCalculator.calculateActiveItemAmount(order);

        BigDecimal totalPaid = paymentRepository
                .sumAmountByOrderIdAndPaymentStatus(orderId, PaymentStatus.COMPLETED);

        BigDecimal refunded = refundRepository.sumRefundAmountByOrderId(orderId);

        FinancialSnapshot snapshot = FinancialSnapshot.capture(
                order,
                gross,
                active,
                totalPaid,
                refunded
        );

        return snapshotRepository.save(snapshot);
    }

    @Override
    public FinancialSnapshot getLatestSnapshot(UUID orderId) {
        return snapshotRepository
                .findTopByOrderIdOrderByCapturedAtDesc(orderId)
                .orElseThrow(() -> new IllegalStateException("No snapshot exists for order"));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<FinancialSnapshot> findAll(UUID orderId) {

        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order not found");
        }

        return snapshotRepository.findByOrderIdOrderByCapturedAtDesc(orderId);
    }
}

package com.teamsynk.canteenpos.payment.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.enums.OrderStatus;
import com.teamsynk.canteenpos.payment.dto.request.PaymentRequestDto;
import com.teamsynk.canteenpos.payment.dto.response.PaymentResponseDto;
import com.teamsynk.canteenpos.payment.entity.Payment;
import com.teamsynk.canteenpos.payment.mapper.PaymentMapper;
import com.teamsynk.canteenpos.payment.repository.PaymentRepository;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.financial.OrderFinancialCalculator;
import com.teamsynk.canteenpos.sales.repository.OrderRepository;

@Service
@Transactional
public class DefaultPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentAggregationService paymentAggregationService;
    private final OrderFinancialCalculator orderFinancialCalculator;

    public DefaultPaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            PaymentAggregationService paymentAggregationService,
            OrderFinancialCalculator orderFinancialCalculator
    ) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentAggregationService = paymentAggregationService;
        this.orderFinancialCalculator = orderFinancialCalculator;
    }

    @Override
    public PaymentResponseDto initiatePayment(PaymentRequestDto request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getSalesStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot pay for cancelled order");
        }

        BigDecimal payable = orderFinancialCalculator.calculateNetAmount(order);
        BigDecimal alreadyPaid = paymentAggregationService.getTotalCompletedPaid(order.getId());

        BigDecimal remaining = payable.subtract(alreadyPaid);

        if (request.getAmount().compareTo(remaining) > 0) {
            throw new IllegalArgumentException("Payment exceeds payable amount");
        }

        Payment payment = Payment.createPayment(
                order,
                request.getAmount(),
                request.getPaymentMode(),
                request.getTransactionRef()
        );

        paymentRepository.save(payment);
        return PaymentMapper.toDto(payment);
    }

    @Override
    public PaymentResponseDto completePayment(UUID paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.markAsCompleted();
        paymentRepository.save(payment);

        updateOrderPaymentStatus(payment.getOrder());
        return PaymentMapper.toDto(payment);
    }

    @Override
    public PaymentResponseDto failPayment(UUID paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.markAsFailed();
        paymentRepository.save(payment);

        return PaymentMapper.toDto(payment);
    }

    private void updateOrderPaymentStatus(Order order) {

        BigDecimal payable = orderFinancialCalculator.calculateNetAmount(order);
        BigDecimal paid = paymentAggregationService.getTotalCompletedPaid(order.getId());

        if (paid.compareTo(BigDecimal.ZERO) > 0 &&
            paid.compareTo(payable) < 0) {
            order.setSalesStatus(OrderStatus.PARTIALLY_PAID);
        }

        if (paid.compareTo(payable) >= 0) {
            order.setSalesStatus(OrderStatus.PAID);
        }
    }
}

package com.teamsynk.canteenpos.sales.financial;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.common.enums.OrderItemStatus;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.entity.OrderItem;

@Component
public class DefaultOrderFinancialCalculator implements OrderFinancialCalculator {

    private static final int MONEY_SCALE = 2;

    @Override
    public BigDecimal calculateGrossAmount(Order order) {
        return order.getOrderItems()
                .stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateActiveItemAmount(Order order) {
        return order.getOrderItems()
                .stream()
                .filter(item -> item.getStatus() == OrderItemStatus.ACTIVE)
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateDiscountAmount(Order order) {
        
        return BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTaxAmount(Order order) {
        
        return BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateNetAmount(Order order) {
        BigDecimal activeAmount = calculateActiveItemAmount(order);
        BigDecimal discount = calculateDiscountAmount(order);
        BigDecimal tax = calculateTaxAmount(order);

        return activeAmount
                .subtract(discount)
                .add(tax)
                .max(BigDecimal.ZERO)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateCancelledItemAmount(Order order) {

        return order.getOrderItems()
                .stream()
                .filter(item -> item.getStatus() == OrderItemStatus.CANCELLED)
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

}

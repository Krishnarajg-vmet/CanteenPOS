package com.teamsynk.canteenpos.sales.financial;

import java.math.BigDecimal;

import com.teamsynk.canteenpos.sales.entity.Order;

public interface OrderFinancialCalculator {

    BigDecimal calculateGrossAmount(Order order);

    BigDecimal calculateActiveItemAmount(Order order);

    BigDecimal calculateDiscountAmount(Order order);

    BigDecimal calculateTaxAmount(Order order);

    BigDecimal calculateNetAmount(Order order);

	BigDecimal calculateCancelledItemAmount(Order order);
}

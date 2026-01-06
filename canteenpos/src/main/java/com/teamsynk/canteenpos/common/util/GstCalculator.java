package com.teamsynk.canteenpos.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GstCalculator {
	
	private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final int SCALE = 2;

    public static BigDecimal calculateGstAmount(BigDecimal basePrice, double gstRatePercent) {
        if (basePrice == null || gstRatePercent <= 0) return BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
        
        BigDecimal rate = BigDecimal.valueOf(gstRatePercent);
        return basePrice.multiply(rate).divide(HUNDRED, SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal[] splitGst(BigDecimal totalGstAmount) {
        BigDecimal half = totalGstAmount.divide(BigDecimal.valueOf(2), SCALE, RoundingMode.HALF_UP);
        return new BigDecimal[]{half, half};
    }

    public static BigDecimal getBasePriceFromInclusive(BigDecimal inclusivePrice, double gstRatePercent) {
        BigDecimal divisor = HUNDRED.add(BigDecimal.valueOf(gstRatePercent));
        return inclusivePrice.multiply(HUNDRED).divide(divisor, SCALE, RoundingMode.HALF_UP);
    }

}

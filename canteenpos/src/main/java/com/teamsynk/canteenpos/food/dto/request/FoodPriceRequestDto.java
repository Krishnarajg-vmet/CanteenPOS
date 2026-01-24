package com.teamsynk.canteenpos.food.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodPriceRequestDto {

	private UUID foodId;
	private UUID branchId;
	private BigDecimal price;
	private String currencyCode;
	private LocalDate effectiveFrom;
	private LocalDate effectiveTo;
	
	public FoodPriceRequestDto(UUID foodId, UUID branchId, BigDecimal price, String currencyCode,
			LocalDate effectiveFrom, LocalDate effectiveTo) {
		this.foodId = foodId;
		this.branchId = branchId;
		this.price = price;
		this.currencyCode = currencyCode;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
	}
	public UUID getFoodId() {
		return foodId;
	}
	public UUID getBranchId() {
		return branchId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}
	public LocalDate getEffectiveTo() {
		return effectiveTo;
	}

	
}

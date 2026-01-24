package com.teamsynk.canteenpos.food.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodPriceResponseDto {

	private UUID id;
	private UUID foodId;
	private String foodName;
	private UUID branchId;
	private String branchName;
	private BigDecimal price;
	private String currencyCode;
	private LocalDate effectiveFrom;
	private LocalDate effectiveTo;
	private Boolean isCurrent;
	
	public FoodPriceResponseDto(UUID id, UUID foodId, String foodName, UUID branchId, String branchName,
			BigDecimal price, String currencyCode, LocalDate effectiveFrom, LocalDate effectiveTo, Boolean isCurrent) {
		this.id = id;
		this.foodId = foodId;
		this.foodName = foodName;
		this.branchId = branchId;
		this.branchName = branchName;
		this.price = price;
		this.currencyCode = currencyCode;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.isCurrent = isCurrent;
	}
	public UUID getId() {
		return id;
	}
	public UUID getFoodId() {
		return foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public UUID getBranchId() {
		return branchId;
	}
	public String getBranchName() {
		return branchName;
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
	public Boolean getIsCurrent() {
		return isCurrent;
	}
}

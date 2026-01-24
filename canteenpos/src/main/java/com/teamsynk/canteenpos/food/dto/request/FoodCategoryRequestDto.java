package com.teamsynk.canteenpos.food.dto.request;

import java.util.UUID;

public class FoodCategoryRequestDto {

	private String foodCategoryCode;
	private String foodCategoryName;
	private UUID parentCategoryId;
	private Integer sortOrder;
	
	public FoodCategoryRequestDto(String foodCategoryCode, String foodCategoryName, UUID parentCategoryId,
			Integer sortOrder) {
		super();
		this.foodCategoryCode = foodCategoryCode;
		this.foodCategoryName = foodCategoryName;
		this.parentCategoryId = parentCategoryId;
		this.sortOrder = sortOrder;
	}
	public String getFoodCategoryCode() {
		return foodCategoryCode;
	}
	public String getFoodCategoryName() {
		return foodCategoryName;
	}
	public UUID getParentCategoryId() {
		return parentCategoryId;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}

}

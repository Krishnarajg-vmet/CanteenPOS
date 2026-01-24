package com.teamsynk.canteenpos.food.dto.response;

import java.util.UUID;

public class FoodCategoryResponseDto {

	private UUID id;
	private String foodCategoryCode;
	private String foodCategoryName;
	private UUID parentCategoryId;
	private String parentCategoryName;
	private Integer sortOrder;
	
	public FoodCategoryResponseDto(UUID id, String foodCategoryCode, String foodCategoryName, UUID parentCategoryId,
			String parentCategoryName, Integer sortOrder) {
		super();
		this.id = id;
		this.foodCategoryCode = foodCategoryCode;
		this.foodCategoryName = foodCategoryName;
		this.parentCategoryId = parentCategoryId;
		this.parentCategoryName = parentCategoryName;
		this.sortOrder = sortOrder;
	}
	public UUID getId() {
		return id;
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
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
}

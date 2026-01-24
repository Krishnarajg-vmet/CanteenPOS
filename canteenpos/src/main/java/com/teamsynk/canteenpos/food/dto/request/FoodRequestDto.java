package com.teamsynk.canteenpos.food.dto.request;

import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.FoodStatus;

public class FoodRequestDto {

	private String foodName;
    private String foodCode;
    private UUID foodCategoryId;
    private UUID foodTypeId;

    private String description;
    private UUID uomId;
    private FoodStatus foodStatus;
	
	public FoodRequestDto(String foodName, String foodCode, String description, UUID foodCategoryId, UUID foodTypeId,
			FoodStatus foodStatus, UUID uomId) {
		super();
		this.foodName = foodName;
		this.foodCode = foodCode;
		this.description = description;
		this.foodCategoryId = foodCategoryId;
		this.foodTypeId = foodTypeId;
		this.foodStatus = foodStatus;
		this.uomId = uomId;
	}
	
	public String getFoodName() {
		return foodName;
	}
	public String getFoodCode() {
		return foodCode;
	}
	public String getDescription() {
		return description;
	}
	public UUID getFoodCategoryId() {
		return foodCategoryId;
	}
	public UUID getFoodTypeId() {
		return foodTypeId;
	}
	public FoodStatus getFoodStatus() {
		return foodStatus;
	}
	public UUID getUomId() {
		return uomId;
	}
}

package com.teamsynk.canteenpos.food.dto.response;

import java.util.UUID;

public class FoodTypeResponseDto {

	private UUID id;
    private String foodTypeCode;
    private String foodTypeName;
    
	public FoodTypeResponseDto(UUID id, String foodTypeCode, String foodTypeName) {
		super();
		this.id = id;
		this.foodTypeCode = foodTypeCode;
		this.foodTypeName = foodTypeName;
	}
	public UUID getId() {
		return id;
	}
	public String getFoodTypeCode() {
		return foodTypeCode;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
}

package com.teamsynk.canteenpos.food.dto.request;

public class FoodTypeRequestDto {

	private String foodTypeCode;
	private String foodTypeName;
	
	public FoodTypeRequestDto(String foodTypeCode, String foodTypeName) {
		this.foodTypeCode = foodTypeCode;
		this.foodTypeName = foodTypeName;
	}
	public String getFoodTypeCode() {
		return foodTypeCode;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
}

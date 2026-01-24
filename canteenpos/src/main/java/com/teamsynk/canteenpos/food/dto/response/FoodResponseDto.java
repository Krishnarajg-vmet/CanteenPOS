package com.teamsynk.canteenpos.food.dto.response;

import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.FoodStatus;

public class FoodResponseDto {

	private UUID id;

    private String foodName;
    private String foodCode;
    private String description;

    private UUID foodCategoryId;
    private String foodCategoryCode;
    private String foodCategoryName;

    private UUID foodTypeId;
    private String foodTypeCode;
    private String foodTypeName;

    private UUID uomId;
    private String uomCode;
    private String uomName;

    private FoodStatus foodStatus;

	
	public FoodResponseDto(UUID id, String foodName, String foodCode, String description, UUID foodCategoryId,
			String foodCategoryName, String foodCategoryCode, UUID foodTypeId, String foodTypeCode,
			String foodTypeName, UUID uomId, String uomCode, String uomName) {
		this.id = id;
		this.foodName = foodName;
		this.foodCode = foodCode;
		this.description = description;
		this.foodCategoryId = foodCategoryId;
		this.foodCategoryName = foodCategoryName;
		this.foodCategoryCode = foodCategoryCode;
		this.foodTypeId = foodTypeId;
		this.foodTypeCode = foodTypeCode;
		this.foodTypeName = foodTypeName;
		this.uomId = uomId;
		this.uomCode = uomCode;
		this.uomName = uomName;
	}
	public UUID getId() {
		return id;
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
	public String getFoodCategoryName() {
		return foodCategoryName;
	}
	public String getFoodCategoryCode() {
		return foodCategoryCode;
	}
	public UUID getFoodTypeId() {
		return foodTypeId;
	}
	public String getFoodTypeCode() {
		return foodTypeCode;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
	public FoodStatus getFoodStatus() {
		return foodStatus;
	}
	public UUID getUomId() {
		return uomId;
	}
	public String getUomCode() {
		return uomCode;
	}
	public String getUomName() {
		return uomName;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFoodCategoryId(UUID foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}
	public void setFoodCategoryName(String foodCategoryName) {
		this.foodCategoryName = foodCategoryName;
	}
	public void setFoodCategoryCode(String foodCategoryCode) {
		this.foodCategoryCode = foodCategoryCode;
	}
	public void setFoodTypeId(UUID foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public void setFoodTypeCode(String foodTypeCode) {
		this.foodTypeCode = foodTypeCode;
	}
	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
	public void setFoodStatus(FoodStatus foodStatus) {
		this.foodStatus = foodStatus;
	}
}

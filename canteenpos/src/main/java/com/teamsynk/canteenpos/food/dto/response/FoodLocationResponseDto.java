package com.teamsynk.canteenpos.food.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public class FoodLocationResponseDto {

    private UUID id;

    private UUID foodId;
    private String foodName;

    private UUID locationId;
    private String locationName;

    private Boolean isSellable;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    
    public FoodLocationResponseDto(UUID id, UUID foodId, String foodName, UUID locationId, String locationName,
			Boolean isSellable, LocalDate effectiveFrom, LocalDate effectiveTo) {
		super();
		this.id = id;
		this.foodId = foodId;
		this.foodName = foodName;
		this.locationId = locationId;
		this.locationName = locationName;
		this.isSellable = isSellable;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
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
    public UUID getLocationId() {
        return locationId;
    }
    public String getLocationName() {
        return locationName;
    }
    public Boolean getIsSellable() {
        return isSellable;
    }
    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }
    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

}

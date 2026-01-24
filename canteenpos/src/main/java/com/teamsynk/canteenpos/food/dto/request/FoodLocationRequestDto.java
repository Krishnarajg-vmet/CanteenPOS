package com.teamsynk.canteenpos.food.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public class FoodLocationRequestDto {

    private UUID foodId;
    private UUID locationId;
    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;
    private Boolean isSellable;

    public FoodLocationRequestDto(UUID foodId, UUID locationId, LocalDate effectiveFrom, LocalDate effectiveTo,
			Boolean isSellable) {
		super();
		this.foodId = foodId;
		this.locationId = locationId;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.isSellable = isSellable;
	}
	public UUID getFoodId() {
        return foodId;
    }
    public UUID getLocationId() {
        return locationId;
    }
    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }
    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }
    public Boolean getIsSellable() {
        return isSellable;
    }
}

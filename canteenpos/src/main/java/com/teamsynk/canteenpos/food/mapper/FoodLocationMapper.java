package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.food.dto.request.FoodLocationRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodLocationResponseDto;
import com.teamsynk.canteenpos.food.entity.Food;
import com.teamsynk.canteenpos.food.entity.FoodLocation;
import com.teamsynk.canteenpos.organization.entity.Branch;

@Component
public class FoodLocationMapper {

	public static FoodLocation toEntity(FoodLocationRequestDto dto, Food food, Branch branch) {
        FoodLocation location = new FoodLocation();
        location.setFood(food);
        location.setLocation(branch);
        location.setEffectiveFrom(dto.getEffectiveFrom());
        location.setEffectiveTo(dto.getEffectiveTo());
        location.setIsSellable(dto.getIsSellable() != null ? dto.getIsSellable() : true);
        return location;
    }

    public static FoodLocationResponseDto toDto(FoodLocation entity) {
        return new FoodLocationResponseDto(
                entity.getId(),
                entity.getFood() != null ? entity.getFood().getId() : null,
                entity.getFood() != null ? entity.getFood().getFoodName() : null,
                entity.getLocation() != null ? entity.getLocation().getId() : null,
                entity.getLocation() != null ? entity.getLocation().getBranchName() : null,
                entity.getIsSellable(),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo()
        );
    }
}

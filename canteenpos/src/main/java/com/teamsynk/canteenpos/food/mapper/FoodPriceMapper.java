package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.food.dto.request.FoodPriceRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodPriceResponseDto;
import com.teamsynk.canteenpos.food.entity.Food;
import com.teamsynk.canteenpos.food.entity.FoodPrice;
import com.teamsynk.canteenpos.organization.entity.Branch;

@Component
public class FoodPriceMapper {

    public static FoodPrice toEntity(FoodPriceRequestDto dto, Food food, Branch branch) {
        FoodPrice price = new FoodPrice();
        price.setFood(food);
        price.setLocation(branch);
        price.setPrice(dto.getPrice());
        price.setCurrencyCode(dto.getCurrencyCode());
        price.setEffectiveFrom(dto.getEffectiveFrom());
        price.setEffectiveTo(dto.getEffectiveTo());
        price.setIsCurrent(true);
        return price;
    }

    public static FoodPriceResponseDto toDto(FoodPrice entity) {
        return new FoodPriceResponseDto(
                entity.getId(),
                entity.getFood() != null ? entity.getFood().getId() : null,
                entity.getFood() != null ? entity.getFood().getFoodName() : null,
                entity.getLocation() != null ? entity.getLocation().getId() : null,
                entity.getLocation() != null ? entity.getLocation().getBranchName() : null,
                entity.getPrice(),
                entity.getCurrencyCode(),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo(),
                entity.getIsCurrent()
        );
    }
}

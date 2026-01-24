package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.food.dto.request.FoodTypeRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodTypeResponseDto;
import com.teamsynk.canteenpos.food.entity.FoodType;

@Component
public class FoodTypeMapper {

    public static FoodType toEntity(FoodTypeRequestDto dto) {
        FoodType foodType = new FoodType();
        foodType.setFoodTypeCode(dto.getFoodTypeCode());
        foodType.setFoodTypeName(dto.getFoodTypeName());
        return foodType;
    }

    public static FoodTypeResponseDto toDto(FoodType entity) {
        return new FoodTypeResponseDto(
                entity.getId(),
                entity.getFoodTypeCode(),
                entity.getFoodTypeName()
        );
    }
}

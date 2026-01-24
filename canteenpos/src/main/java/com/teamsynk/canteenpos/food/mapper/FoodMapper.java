package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;
import com.teamsynk.canteenpos.food.dto.request.FoodRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodResponseDto;
import com.teamsynk.canteenpos.food.entity.Food;
import com.teamsynk.canteenpos.food.entity.FoodCategory;
import com.teamsynk.canteenpos.food.entity.FoodType;
import com.teamsynk.canteenpos.food.entity.Uom;

@Component
public class FoodMapper {

    public static Food toEntity(
            FoodRequestDto dto,
            FoodCategory category,
            FoodType type,
            Uom uom
    ) {
        Food food = new Food();
        food.setFoodName(dto.getFoodName());
        food.setFoodCode(dto.getFoodCode());
        food.setDescription(dto.getDescription());
        food.setFoodCategory(category);
        food.setFoodType(type);
        food.setUom(uom);

        if (dto.getFoodStatus() != null) {
            food.setFoodStatus(dto.getFoodStatus());
        }

        return food;
    }

    public static FoodResponseDto toDto(Food food) {
        FoodResponseDto dto = new FoodResponseDto(
                food.getId(),
                food.getFoodName(),
                food.getFoodCode(),
                food.getDescription(),
                food.getFoodCategory() != null ? food.getFoodCategory().getId() : null,
                food.getFoodCategory() != null ? food.getFoodCategory().getFoodCategoryName() : null,
                food.getFoodCategory() != null ? food.getFoodCategory().getFoodCategoryCode() : null,
                food.getFoodType() != null ? food.getFoodType().getId() : null,
                food.getFoodType() != null ? food.getFoodType().getFoodTypeCode() : null,
                food.getFoodType() != null ? food.getFoodType().getFoodTypeName() : null,
                food.getUom() != null ? food.getUom().getId() : null,
                food.getUom() != null ? food.getUom().getUomCode() : null,
                food.getUom() != null ? food.getUom().getUomName() : null
        );

        dto.setFoodStatus(food.getFoodStatus());

        return dto;
    }
}

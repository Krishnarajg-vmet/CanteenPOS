package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.food.dto.request.FoodCategoryRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodCategoryResponseDto;
import com.teamsynk.canteenpos.food.entity.FoodCategory;

@Component
public class FoodCategoryMapper {

    public static FoodCategory toEntity(FoodCategoryRequestDto dto, FoodCategory parentCategory) {
        FoodCategory category = new FoodCategory();
        category.setFoodCategoryCode(dto.getFoodCategoryCode());
        category.setFoodCategoryName(dto.getFoodCategoryName());
        category.setSortOrder(dto.getSortOrder());
        category.setParentCategory(parentCategory);
        return category;
    }

    public static FoodCategoryResponseDto toDto(FoodCategory category) {
        return new FoodCategoryResponseDto(
                category.getId(),
                category.getFoodCategoryCode(),
                category.getFoodCategoryName(),
                category.getParentCategory() != null ? category.getParentCategory().getId() : null,
                category.getParentCategory() != null ? category.getParentCategory().getFoodCategoryName() : null,
                category.getSortOrder()
        );
    }
}


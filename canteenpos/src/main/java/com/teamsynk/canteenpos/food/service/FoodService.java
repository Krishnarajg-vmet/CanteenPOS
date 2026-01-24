package com.teamsynk.canteenpos.food.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.enums.FoodStatus;
import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.FoodRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodResponseDto;
import com.teamsynk.canteenpos.food.entity.Food;
import com.teamsynk.canteenpos.food.entity.FoodCategory;
import com.teamsynk.canteenpos.food.entity.FoodType;
import com.teamsynk.canteenpos.food.entity.Uom;
import com.teamsynk.canteenpos.food.mapper.FoodMapper;
import com.teamsynk.canteenpos.food.repository.FoodRepository;
import com.teamsynk.canteenpos.food.repository.FoodCategoryRepository;
import com.teamsynk.canteenpos.food.repository.FoodTypeRepository;
import com.teamsynk.canteenpos.food.repository.UomRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodCategoryRepository categoryRepository;
    private final FoodTypeRepository typeRepository;
    private final UomRepository uomRepository;

    public FoodService(FoodRepository foodRepository,
                       FoodCategoryRepository categoryRepository,
                       FoodTypeRepository typeRepository,
                       UomRepository uomRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
        this.uomRepository = uomRepository;
    }

    @Transactional
    public FoodResponseDto createFood(FoodRequestDto dto) {

        if (foodRepository.existsByFoodNameAndFoodCategory_IdAndIsActiveTrue(
                dto.getFoodName(), dto.getFoodCategoryId())) {
            throw new IllegalArgumentException("Food already exists in this category");
        }

        FoodCategory category = categoryRepository.findById(dto.getFoodCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("FoodCategory", dto.getFoodCategoryId()));

        FoodType type = typeRepository.findById(dto.getFoodTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("FoodType", dto.getFoodTypeId()));

        Uom uom = null;
        if (dto.getUomId() != null) {
            uom = uomRepository.findById(dto.getUomId())
                    .orElseThrow(() -> new ResourceNotFoundException("UOM", dto.getUomId()));
        }

        Food food = FoodMapper.toEntity(dto, category, type, uom);
        return FoodMapper.toDto(foodRepository.save(food));
    }


    public FoodResponseDto getFoodById(UUID id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food", id));
        return FoodMapper.toDto(food);
    }

    @Transactional
    public FoodResponseDto updateFood(UUID id, FoodRequestDto dto) {

        Food existing = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food", id));

        if (dto.getFoodCategoryId() != null &&
            !dto.getFoodCategoryId().equals(existing.getFoodCategory().getId())) {

            FoodCategory category = categoryRepository.findById(dto.getFoodCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("FoodCategory", dto.getFoodCategoryId()));
            existing.setFoodCategory(category);
        }

        if (dto.getFoodTypeId() != null &&
            !dto.getFoodTypeId().equals(existing.getFoodType().getId())) {

            FoodType type = typeRepository.findById(dto.getFoodTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("FoodType", dto.getFoodTypeId()));
            existing.setFoodType(type);
        }

        if (dto.getUomId() != null) {
            Uom uom = uomRepository.findById(dto.getUomId())
                    .orElseThrow(() -> new ResourceNotFoundException("UOM", dto.getUomId()));
            existing.setUom(uom);
        }

        if (dto.getFoodStatus() != null &&
            dto.getFoodStatus() != existing.getFoodStatus()) {

            validateStatusTransition(existing.getFoodStatus(), dto.getFoodStatus());
            existing.setFoodStatus(dto.getFoodStatus());
        }

        existing.setFoodName(dto.getFoodName());
        existing.setFoodCode(dto.getFoodCode());
        existing.setDescription(dto.getDescription());

        return FoodMapper.toDto(foodRepository.save(existing));
    }

    public List<FoodResponseDto> getAllFood() {
        return foodRepository.findAll().stream()
                .map(FoodMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<FoodResponseDto> getAllActiveFood() {
        return foodRepository.findAllByIsActiveTrue()
                .stream()
                .map(FoodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteFood(UUID id) {
        Food existing = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food", id));
        existing.setIsActive(false);
        existing.setFoodStatus(FoodStatus.SUSPENDED);
        foodRepository.save(existing);
    }
    
    private void validateStatusTransition(FoodStatus current, FoodStatus next) {

        if (current == FoodStatus.SUSPENDED && next == FoodStatus.ACTIVE) {
            throw new IllegalStateException("Suspended food cannot be reactivated");
        }

        if (current == FoodStatus.ACTIVE && next == FoodStatus.DRAFT) {
            throw new IllegalStateException("Active food cannot move back to draft");
        }
    }

}

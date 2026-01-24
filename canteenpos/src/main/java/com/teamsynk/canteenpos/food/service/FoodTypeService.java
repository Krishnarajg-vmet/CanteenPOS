package com.teamsynk.canteenpos.food.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.FoodTypeRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodTypeResponseDto;
import com.teamsynk.canteenpos.food.entity.FoodType;
import com.teamsynk.canteenpos.food.mapper.FoodTypeMapper;
import com.teamsynk.canteenpos.food.repository.FoodTypeRepository;

@Service
public class FoodTypeService {

    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeService(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Transactional
    public FoodTypeResponseDto createFoodType(FoodTypeRequestDto dto) {
        if (foodTypeRepository.existsByFoodTypeCode(dto.getFoodTypeCode())) {
            throw new IllegalArgumentException("Food type code already exists");
        }
        if (foodTypeRepository.existsByFoodTypeName(dto.getFoodTypeName())) {
            throw new IllegalArgumentException("Food type name already exists");
        }

        FoodType entity = FoodTypeMapper.toEntity(dto);
        return FoodTypeMapper.toDto(foodTypeRepository.save(entity));
    }

    public FoodTypeResponseDto getFoodTypeById(UUID id) {
        FoodType entity = foodTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodType", id));
        return FoodTypeMapper.toDto(entity);
    }

    public List<FoodTypeResponseDto> getAllFoodTypes() {
        return foodTypeRepository.findAll().stream()
                .map(FoodTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<FoodTypeResponseDto> getAllActiveFoodTypes() {
        return foodTypeRepository.findAllByIsActiveTrue().stream()
                .map(FoodTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FoodTypeResponseDto updateFoodType(UUID id, FoodTypeRequestDto dto) {
        FoodType existing = foodTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodType", id));

        if (!existing.getFoodTypeCode().equals(dto.getFoodTypeCode())
                && foodTypeRepository.existsByFoodTypeCode(dto.getFoodTypeCode())) {
            throw new IllegalArgumentException("Food type code already exists");
        }

        if (!existing.getFoodTypeName().equals(dto.getFoodTypeName())
                && foodTypeRepository.existsByFoodTypeName(dto.getFoodTypeName())) {
            throw new IllegalArgumentException("Food type name already exists");
        }

        existing.setFoodTypeCode(dto.getFoodTypeCode());
        existing.setFoodTypeName(dto.getFoodTypeName());

        return FoodTypeMapper.toDto(foodTypeRepository.save(existing));
    }

    @Transactional
    public void deleteFoodType(UUID id) {
        FoodType existing = foodTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodType", id));
        existing.setIsActive(false);
        foodTypeRepository.save(existing);
    }
}

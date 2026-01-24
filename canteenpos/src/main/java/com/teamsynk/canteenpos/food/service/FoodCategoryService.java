package com.teamsynk.canteenpos.food.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.FoodCategoryRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodCategoryResponseDto;
import com.teamsynk.canteenpos.food.entity.FoodCategory;
import com.teamsynk.canteenpos.food.mapper.FoodCategoryMapper;
import com.teamsynk.canteenpos.food.repository.FoodCategoryRepository;

@Service
public class FoodCategoryService {

    private final FoodCategoryRepository categoryRepository;

    public FoodCategoryService(FoodCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public FoodCategoryResponseDto createCategory(FoodCategoryRequestDto dto) {

        categoryRepository.findByFoodCategoryCodeAndIsActiveTrue(dto.getFoodCategoryCode())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Food category code already exists");
                });

        FoodCategory parent = null;
        if (dto.getParentCategoryId() != null) {
            parent = categoryRepository.findById(dto.getParentCategoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "FoodCategory", dto.getParentCategoryId()));
        }

        FoodCategory category = FoodCategoryMapper.toEntity(dto, parent);
        return FoodCategoryMapper.toDto(categoryRepository.save(category));
    }

    public FoodCategoryResponseDto getCategoryById(UUID id) {
        FoodCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodCategory", id));
        return FoodCategoryMapper.toDto(category);
    }

    public List<FoodCategoryResponseDto> getAllActiveCategories() {
        return categoryRepository.findAllByIsActiveTrueOrderBySortOrderAsc()
                .stream()
                .map(FoodCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<FoodCategoryResponseDto> getChildCategories(UUID parentCategoryId) {
        return categoryRepository.findAllByParentCategory_IdAndIsActiveTrue(parentCategoryId)
                .stream()
                .map(FoodCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FoodCategoryResponseDto updateCategory(UUID id, FoodCategoryRequestDto dto) {

        FoodCategory existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodCategory", id));

        if (!existing.getFoodCategoryCode().equals(dto.getFoodCategoryCode())) {
            categoryRepository.findByFoodCategoryCodeAndIsActiveTrue(dto.getFoodCategoryCode())
                    .ifPresent(c -> {
                        throw new IllegalArgumentException("Food category code already exists");
                    });
        }

        existing.setFoodCategoryCode(dto.getFoodCategoryCode());
        existing.setFoodCategoryName(dto.getFoodCategoryName());
        existing.setSortOrder(dto.getSortOrder());

        if (dto.getParentCategoryId() != null) {
            if (dto.getParentCategoryId().equals(id)) {
                throw new IllegalStateException("Category cannot be its own parent");
            }

            FoodCategory parent = categoryRepository.findById(dto.getParentCategoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "FoodCategory", dto.getParentCategoryId()));
            existing.setParentCategory(parent);
        } else {
            existing.setParentCategory(null);
        }

        return FoodCategoryMapper.toDto(categoryRepository.save(existing));
    }

    @Transactional
    public void deleteCategory(UUID id) {

        FoodCategory existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodCategory", id));

        if (!categoryRepository
                .findAllByParentCategory_IdAndIsActiveTrue(id)
                .isEmpty()) {
            throw new IllegalStateException("Cannot delete category with active subcategories");
        }

        existing.setIsActive(false);
        categoryRepository.save(existing);
    }
}

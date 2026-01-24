package com.teamsynk.canteenpos.food.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.FoodCategoryRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodCategoryResponseDto;
import com.teamsynk.canteenpos.food.service.FoodCategoryService;

@RestController
@RequestMapping("/api/v1/food-categories")
public class FoodCategoryRestController {

    private final FoodCategoryService categoryService;

    public FoodCategoryRestController(FoodCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<FoodCategoryResponseDto> createCategory(
            @Valid @RequestBody FoodCategoryRequestDto dto) {

        FoodCategoryResponseDto response = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodCategoryResponseDto> getCategoryById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<FoodCategoryResponseDto>> getAllActiveCategories() {
        return ResponseEntity.ok(categoryService.getAllActiveCategories());
    }

    @GetMapping("/{id}/children")
    public ResponseEntity<List<FoodCategoryResponseDto>> getChildCategories(
            @PathVariable UUID id) {

        return ResponseEntity.ok(categoryService.getChildCategories(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodCategoryResponseDto> updateCategory(
            @PathVariable UUID id,
            @Valid @RequestBody FoodCategoryRequestDto dto) {

        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

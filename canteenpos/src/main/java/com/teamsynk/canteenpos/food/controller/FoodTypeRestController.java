package com.teamsynk.canteenpos.food.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.FoodTypeRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodTypeResponseDto;
import com.teamsynk.canteenpos.food.service.FoodTypeService;

@RestController
@RequestMapping("/api/v1/food-types")
public class FoodTypeRestController {

    private final FoodTypeService foodTypeService;

    public FoodTypeRestController(FoodTypeService foodTypeService) {
        this.foodTypeService = foodTypeService;
    }

    @PostMapping
    public ResponseEntity<FoodTypeResponseDto> createFoodType(
            @Valid @RequestBody FoodTypeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodTypeService.createFoodType(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodTypeResponseDto> getFoodTypeById(@PathVariable UUID id) {
        return ResponseEntity.ok(foodTypeService.getFoodTypeById(id));
    }

    @GetMapping
    public ResponseEntity<List<FoodTypeResponseDto>> getAllFoodTypes() {
        return ResponseEntity.ok(foodTypeService.getAllFoodTypes());
    }

    @GetMapping("/active")
    public ResponseEntity<List<FoodTypeResponseDto>> getAllActiveFoodTypes() {
        return ResponseEntity.ok(foodTypeService.getAllActiveFoodTypes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodTypeResponseDto> updateFoodType(
            @PathVariable UUID id,
            @Valid @RequestBody FoodTypeRequestDto dto) {
        return ResponseEntity.ok(foodTypeService.updateFoodType(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodType(@PathVariable UUID id) {
        foodTypeService.deleteFoodType(id);
        return ResponseEntity.noContent().build();
    }
}

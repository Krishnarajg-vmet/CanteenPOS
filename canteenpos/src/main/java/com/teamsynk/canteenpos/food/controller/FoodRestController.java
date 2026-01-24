package com.teamsynk.canteenpos.food.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.FoodRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodResponseDto;
import com.teamsynk.canteenpos.food.service.FoodService;

@RestController
@RequestMapping("/api/v1/foods")
public class FoodRestController {

    private final FoodService foodService;

    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<FoodResponseDto> createFood(
            @Valid @RequestBody FoodRequestDto request) {

        FoodResponseDto response = foodService.createFood(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getFoodById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @GetMapping
    public ResponseEntity<List<FoodResponseDto>> getAllActiveFood() {
        return ResponseEntity.ok(foodService.getAllActiveFood());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodResponseDto>> getAllFood() {
        return ResponseEntity.ok(foodService.getAllFood());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDto> updateFood(
            @PathVariable UUID id,
            @Valid @RequestBody FoodRequestDto request) {

        return ResponseEntity.ok(foodService.updateFood(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(
            @PathVariable UUID id) {

        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}

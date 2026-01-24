package com.teamsynk.canteenpos.food.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.FoodPriceRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodPriceResponseDto;
import com.teamsynk.canteenpos.food.service.FoodPriceService;

@RestController
@RequestMapping("/api/v1/food-prices")
public class FoodPriceRestController {

    private final FoodPriceService foodPriceService;

    public FoodPriceRestController(FoodPriceService foodPriceService) {
        this.foodPriceService = foodPriceService;
    }

    @PostMapping
    public ResponseEntity<FoodPriceResponseDto> createPrice(
            @RequestBody FoodPriceRequestDto request
    ) {
        FoodPriceResponseDto response =
                foodPriceService.createPrice(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<FoodPriceResponseDto>> bulkCreateOrUpdate(
            @RequestBody List<FoodPriceRequestDto> requests
    ) {
        return ResponseEntity.ok(
                foodPriceService.bulkCreateOrUpdate(requests)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodPriceResponseDto> getPriceById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                foodPriceService.getPriceById(id)
        );
    }

    @GetMapping("/food/{foodId}/branch/{branchId}")
    public ResponseEntity<List<FoodPriceResponseDto>> getAllPricesByFood(
            @PathVariable UUID foodId,
            @PathVariable UUID branchId
    ) {
        return ResponseEntity.ok(
                foodPriceService.getAllPricesByFood(foodId, branchId)
        );
    }

    @GetMapping("/food/{foodId}/branch/{branchId}/today")
    public ResponseEntity<FoodPriceResponseDto> getPriceForToday(
            @PathVariable UUID foodId,
            @PathVariable UUID branchId
    ) {
        return ResponseEntity.ok(
                foodPriceService.getPriceForToday(foodId, branchId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodPriceResponseDto> updatePrice(
            @PathVariable UUID id,
            @RequestBody FoodPriceRequestDto request
    ) {
        return ResponseEntity.ok(
                foodPriceService.updatePrice(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(
            @PathVariable UUID id
    ) {
        foodPriceService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}

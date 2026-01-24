package com.teamsynk.canteenpos.food.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.FoodLocationRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodLocationResponseDto;
import com.teamsynk.canteenpos.food.service.FoodLocationService;

@RestController
@RequestMapping("/api/v1/food-locations")
public class FoodLocationRestController {

    private final FoodLocationService foodLocationService;

    public FoodLocationRestController(FoodLocationService foodLocationService) {
        this.foodLocationService = foodLocationService;
    }

    @PostMapping
    public ResponseEntity<FoodLocationResponseDto> createFoodLocation(
            @RequestBody FoodLocationRequestDto request
    ) {
        FoodLocationResponseDto response =
                foodLocationService.createLocation(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<FoodLocationResponseDto>> bulkCreateOrUpdate(
            @RequestBody List<FoodLocationRequestDto> requests
    ) {
        return ResponseEntity.ok(
                foodLocationService.bulkCreateOrUpdate(requests)
        );
    }

    @PutMapping("/food/{foodId}/location/{locationId}")
    public ResponseEntity<FoodLocationResponseDto> updateFoodLocation(
            @PathVariable UUID foodId,
            @PathVariable UUID locationId,
            @RequestParam(required = false) Boolean isSellable,
            @RequestParam(required = false) LocalDate effectiveFrom,
            @RequestParam(required = false) LocalDate effectiveTo
    ) {
        return ResponseEntity.ok(
                foodLocationService.updateLocation(
                        foodId,
                        locationId,
                        isSellable,
                        effectiveFrom,
                        effectiveTo
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodLocationResponseDto> getFoodLocationById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                foodLocationService.getLocationById(id)
        );
    }

    @GetMapping("/food/{foodId}")
    public ResponseEntity<List<FoodLocationResponseDto>> getAllLocationsByFood(
            @PathVariable UUID foodId
    ) {
        return ResponseEntity.ok(
                foodLocationService.getAllLocationsByFood(foodId)
        );
    }

    @GetMapping("/sellable")
    public ResponseEntity<FoodLocationResponseDto> getSellableFoodForToday(
            @RequestParam UUID foodId,
            @RequestParam UUID locationId
    ) {
        return ResponseEntity.ok(
                foodLocationService.getSellableFoodForToday(foodId, locationId)
        );
    }

    @DeleteMapping("/food/{foodId}/location/{locationId}")
    public ResponseEntity<Void> deleteFoodLocation(
            @PathVariable UUID foodId,
            @PathVariable UUID locationId
    ) {
        foodLocationService.deleteLocation(foodId, locationId);
        return ResponseEntity.noContent().build();
    }
}

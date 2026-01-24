package com.teamsynk.canteenpos.food.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.UomConversionRequestDto;
import com.teamsynk.canteenpos.food.dto.response.UomConversionResponseDto;
import com.teamsynk.canteenpos.food.service.UomConversionService;

@RestController
@RequestMapping("/api/v1/uom-conversions")
public class UomConversionRestController {

    private final UomConversionService conversionService;

    public UomConversionRestController(UomConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<UomConversionResponseDto> createConversion(@Valid @RequestBody UomConversionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversionService.createConversion(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UomConversionResponseDto> getConversionById(@PathVariable UUID id) {
        return ResponseEntity.ok(conversionService.getConversionById(id));
    }

    @GetMapping
    public ResponseEntity<List<UomConversionResponseDto>> getAllConversions() {
        return ResponseEntity.ok(conversionService.getAllConversions());
    }

    @GetMapping("/active")
    public ResponseEntity<UomConversionResponseDto> getActiveConversion(@RequestParam UUID fromUomId,
                                                                        @RequestParam UUID toUomId) {
        return ResponseEntity.ok(conversionService.getActiveConversion(fromUomId, toUomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UomConversionResponseDto> updateConversion(@PathVariable UUID id,
                                                                    @Valid @RequestBody UomConversionRequestDto dto) {
        return ResponseEntity.ok(conversionService.updateConversion(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversion(@PathVariable UUID id) {
        conversionService.deleteConversion(id);
        return ResponseEntity.noContent().build();
    }
}

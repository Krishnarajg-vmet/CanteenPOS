package com.teamsynk.canteenpos.food.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.food.dto.request.UomRequestDto;
import com.teamsynk.canteenpos.food.dto.response.UomResponseDto;
import com.teamsynk.canteenpos.food.service.UomService;

@RestController
@RequestMapping("/api/v1/uoms")
public class UomRestController {

    private final UomService uomService;

    public UomRestController(UomService uomService) {
        this.uomService = uomService;
    }

    @PostMapping
    public ResponseEntity<UomResponseDto> createUom(@Valid @RequestBody UomRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(uomService.createUom(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UomResponseDto> getUomById(@PathVariable UUID id) {
        return ResponseEntity.ok(uomService.getUomById(id));
    }

    @GetMapping
    public ResponseEntity<List<UomResponseDto>> getAllUoms() {
        return ResponseEntity.ok(uomService.getAllUoms());
    }

    @GetMapping("/active")
    public ResponseEntity<List<UomResponseDto>> getAllActiveUoms() {
        return ResponseEntity.ok(uomService.getAllActiveUoms());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UomResponseDto> updateUom(@PathVariable UUID id,
                                                    @Valid @RequestBody UomRequestDto dto) {
        return ResponseEntity.ok(uomService.updateUom(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUom(@PathVariable UUID id) {
        uomService.deleteUom(id);
        return ResponseEntity.noContent().build();
    }
}

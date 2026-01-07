package com.teamsynk.canteenpos.location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.location.dto.request.CountryRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CountryResponseDto;
import com.teamsynk.canteenpos.location.service.CountryService;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryResponseDto> createCountry(
            @RequestBody CountryRequestDto request) {

        CountryResponseDto response = countryService.createCountry(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponseDto> getCountryById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CountryResponseDto>> getAllActiveCountries() {
        return ResponseEntity.ok(countryService.getAllActiveCountries());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponseDto> updateCountry(
            @PathVariable UUID id,
            @RequestBody CountryRequestDto request) {

        return ResponseEntity.ok(countryService.updateCountry(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable UUID id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}

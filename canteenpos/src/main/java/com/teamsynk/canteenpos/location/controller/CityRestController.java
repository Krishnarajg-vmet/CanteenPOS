package com.teamsynk.canteenpos.location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.location.dto.request.CityRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CityResponseDto;
import com.teamsynk.canteenpos.location.service.CityService;

@RestController
@RequestMapping("/api/v1/cities")
public class CityRestController {
	
	private final CityService cityService;
	
	CityRestController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping
	public ResponseEntity<CityResponseDto> createCity(
			@RequestBody CityRequestDto request) {
		CityResponseDto response = cityService.createCity(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CityResponseDto> getCityById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(cityService.getCityById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CityResponseDto> updateCity(
			@PathVariable UUID id, @RequestBody CityRequestDto dto) {
		CityResponseDto response = cityService.updateCity(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<CityResponseDto>> getAllActiveCities(){
		return ResponseEntity.ok(cityService.getAllActiveCities());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CityResponseDto>> getAllCities(){
		return ResponseEntity.ok(cityService.getAllCities());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCityById(
			@PathVariable UUID id) {
		cityService.deleteCityById(id);
		return ResponseEntity.noContent().build();
	}

}

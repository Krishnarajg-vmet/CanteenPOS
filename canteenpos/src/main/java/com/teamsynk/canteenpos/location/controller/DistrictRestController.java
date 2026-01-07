package com.teamsynk.canteenpos.location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.location.dto.request.DistrictRequestDto;
import com.teamsynk.canteenpos.location.dto.response.DistrictResponseDto;
import com.teamsynk.canteenpos.location.service.DistrictService;

@RestController
@RequestMapping("/api/v1/districts")
public class DistrictRestController {
	
	private final DistrictService districtService;
	
	DistrictRestController(DistrictService districtService) {
		this.districtService = districtService;
	}
	
	@PostMapping
	public ResponseEntity<DistrictResponseDto> createDistrict(
			@RequestBody DistrictRequestDto request) {
		DistrictResponseDto response = districtService.createDistrict(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DistrictResponseDto> getDistrictById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(districtService.getDistrictById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DistrictResponseDto> updateDistrict(
			@PathVariable UUID id, @RequestBody DistrictRequestDto dto) {
		DistrictResponseDto response = districtService.updateDistrict(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<DistrictResponseDto>> getAllActiveDistricts() {
		return ResponseEntity.ok(districtService.getAllActiveDistricts());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DistrictResponseDto>> getAllDistricts() {
		return ResponseEntity.ok(districtService.getAllDistricts());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDistricts(
			@PathVariable UUID id) {
		districtService.deleteDistrictById(id);
		return ResponseEntity.noContent().build();
	}

}

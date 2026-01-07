package com.teamsynk.canteenpos.location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.location.dto.request.AreaRequestDto;
import com.teamsynk.canteenpos.location.dto.response.AreaResponseDto;
import com.teamsynk.canteenpos.location.service.AreaService;

@RestController
@RequestMapping("/api/v1/areas")
public class AreaRestController {
	
	private final AreaService areaService;
	
	AreaRestController(AreaService areaService) {
		this.areaService = areaService;
	}

	@PostMapping
	public ResponseEntity<AreaResponseDto> createArea(
			@RequestBody AreaRequestDto request) {
		AreaResponseDto response = areaService.createArea(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AreaResponseDto> getAreaById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(areaService.getAreaById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AreaResponseDto> updateArea(
			@PathVariable UUID id, @RequestBody AreaRequestDto dto) {
		AreaResponseDto response = areaService.updateArea(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<AreaResponseDto>> getAllActiveAreas(){
		return ResponseEntity.ok(areaService.getAllActiveAreas());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AreaResponseDto>> getAllAreas(){
		return ResponseEntity.ok(areaService.getAllAreas());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAreaById(
			@PathVariable UUID id) {
		areaService.deleteAreaById(id);
		return ResponseEntity.noContent().build();
	}
}

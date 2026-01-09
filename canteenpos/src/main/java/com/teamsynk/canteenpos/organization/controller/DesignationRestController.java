package com.teamsynk.canteenpos.organization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.organization.dto.request.DesignationRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.DesignationResponseDto;
import com.teamsynk.canteenpos.organization.service.DesignationService;

@RestController
@RequestMapping("/api/v1/designations")
public class DesignationRestController {

	private final DesignationService designationService;
	
	DesignationRestController(DesignationService designationService) {
		this.designationService = designationService;
	}
	
	@PostMapping
	public ResponseEntity<DesignationResponseDto> cretaeDesignation(
			@RequestBody DesignationRequestDto request) {
		DesignationResponseDto response = designationService.createDesignation(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DesignationResponseDto> getDesignationById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(designationService.getDesignationById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<DesignationResponseDto>> getAllActiveDesignation() {
		return ResponseEntity.ok(designationService.getAllActiveDesignations());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DesignationResponseDto>> getAllDesignation() {
		return ResponseEntity.ok(designationService.getAllDesignations());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DesignationResponseDto> updateDesignation(
			@PathVariable UUID id, @RequestBody DesignationRequestDto dto) {
		return ResponseEntity.ok(designationService.updateDesignation(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DesignationResponseDto> deleteDesignationById(
			@PathVariable UUID id) {
		designationService.deleteDesignationById(id);
		return ResponseEntity.noContent().build();
	}
}

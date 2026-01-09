package com.teamsynk.canteenpos.organization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.organization.dto.request.DepartmentRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.DepartmentResponseDto;
import com.teamsynk.canteenpos.organization.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentRestController {
	
	private final DepartmentService departmentService;
	
	DepartmentRestController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@PostMapping
	public ResponseEntity<DepartmentResponseDto> createDepartment(
			@RequestBody DepartmentRequestDto request) {
		DepartmentResponseDto response = departmentService.createDepartment(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> getDepartmentById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(departmentService.getDepartmentById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentResponseDto>> getAllActiveDepartment(){
		return ResponseEntity.ok(departmentService.getAllActiveDepartments());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DepartmentResponseDto>> getAllDepartment() {
		return ResponseEntity.ok(departmentService.getAllDepartments());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> updateDepartment(
			@PathVariable UUID id, @RequestBody DepartmentRequestDto dto) {
		return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> deleteDepartmentById(
			@PathVariable UUID id) {
		departmentService.deleteDepartmentById(id);
		return ResponseEntity.noContent().build();
	}

}

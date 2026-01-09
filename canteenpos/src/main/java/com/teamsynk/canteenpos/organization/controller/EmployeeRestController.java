package com.teamsynk.canteenpos.organization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.organization.dto.request.EmployeeRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.EmployeeResponseDto;
import com.teamsynk.canteenpos.organization.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {

	private final EmployeeService employeeService;
	
	EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponseDto> createEmployee(
			@RequestBody EmployeeRequestDto request) {
		EmployeeResponseDto response = employeeService.createEmployee(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> getEmployeeById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getAllActiveEmployees() {
		return ResponseEntity.ok(employeeService.getAllActiveEmployees());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateEmployee(
			@PathVariable UUID id, @RequestBody EmployeeRequestDto dto) {
		return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> deleteEmployeeById(
			@PathVariable UUID id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.noContent().build();
	}
}

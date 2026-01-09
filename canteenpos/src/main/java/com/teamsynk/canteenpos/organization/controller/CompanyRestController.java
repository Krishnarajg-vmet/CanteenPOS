package com.teamsynk.canteenpos.organization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.organization.dto.request.CompanyRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.CompanyResponseDto;
import com.teamsynk.canteenpos.organization.service.CompanyService;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyRestController {
	
	private final CompanyService companyService;
	
	CompanyRestController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@PostMapping
	public ResponseEntity<CompanyResponseDto> createCompany(
			@RequestBody CompanyRequestDto request) {
		CompanyResponseDto response = companyService.createCompany(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyResponseDto> getCompanyById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(companyService.getCompanyById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CompanyResponseDto>> getAllActiveCompanies() {
		return ResponseEntity.ok(companyService.getAllActiveCompanies());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
		return ResponseEntity.ok(companyService.getAllCompanies());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyResponseDto> updateCompanies(@PathVariable UUID id,
			@RequestBody CompanyRequestDto request) {
				
		return ResponseEntity.ok(companyService.updateCompany(id, request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompanyById(
			@PathVariable UUID id) {
		companyService.deleteCompany(id);
		return ResponseEntity.noContent().build();
	}

}

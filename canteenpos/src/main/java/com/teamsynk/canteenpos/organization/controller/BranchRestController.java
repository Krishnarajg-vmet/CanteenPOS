package com.teamsynk.canteenpos.organization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamsynk.canteenpos.organization.dto.request.BranchRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.BranchResponseDto;
import com.teamsynk.canteenpos.organization.service.BranchService;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchRestController {

	private final BranchService branchService;
	
	BranchRestController(BranchService branchService) {
		this.branchService = branchService;
	}
	
	@PostMapping
	public ResponseEntity<BranchResponseDto> createBranch(
			@RequestBody BranchRequestDto request) {
		BranchResponseDto response = branchService.createBranch(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchResponseDto> getBranchById(
			@PathVariable UUID id) {
		return ResponseEntity.ok(branchService.getBranchById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<BranchResponseDto>> getAllActiveBranches(){
		return ResponseEntity.ok(branchService.getAllActiveBranches());
	}

	@GetMapping("/all")
	public ResponseEntity<List<BranchResponseDto>> getAllBranches(){
		return ResponseEntity.ok(branchService.getAllBranches());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BranchResponseDto> updateBranch(
			@PathVariable UUID id, @RequestBody BranchRequestDto dto) {
		return ResponseEntity.ok(branchService.updateBranch(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BranchResponseDto> deleteBranchById(
			@PathVariable UUID id) {
		branchService.deleteBranchById(id);
		return ResponseEntity.noContent().build();
	}
}

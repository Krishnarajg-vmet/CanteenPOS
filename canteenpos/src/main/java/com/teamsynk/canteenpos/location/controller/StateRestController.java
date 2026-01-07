package com.teamsynk.canteenpos.location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.location.dto.request.StateRequestDto;
import com.teamsynk.canteenpos.location.dto.response.StateResponseDto;
import com.teamsynk.canteenpos.location.service.StateService;

@RestController
@RequestMapping("/api/v1/states")
public class StateRestController {
	
	private final StateService stateService;
	
	StateRestController(StateService stateService) {
		this.stateService = stateService;
	}
	
	@PostMapping
	public ResponseEntity<StateResponseDto> createState(
						@RequestBody StateRequestDto request) {
		
		StateResponseDto response = stateService.createState(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StateResponseDto> getStateById(
						@PathVariable UUID id) {
		return ResponseEntity.ok(stateService.getStateById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<StateResponseDto>> getAllActiveStates() {
		return ResponseEntity.ok(stateService.getAllActiveStates());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StateResponseDto>> getAllStates() {
		return ResponseEntity.ok(stateService.getAllStates());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StateResponseDto> updateState(
					@PathVariable UUID id,
					@RequestBody StateRequestDto request) {
		StateResponseDto response = stateService.updateState(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStateById(
					@PathVariable UUID id) {
		stateService.deleteStateById(id);
		return ResponseEntity.noContent().build();
	}

}

package com.teamsynk.canteenpos.organization.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.organization.dto.request.DesignationRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.DesignationResponseDto;
import com.teamsynk.canteenpos.organization.entity.Designation;
import com.teamsynk.canteenpos.organization.mapper.DesignationMapper;
import com.teamsynk.canteenpos.organization.repository.DesignationRepository;

@Service
public class DesignationService {

	private final DesignationRepository designationRepository;
	
	DesignationService(DesignationRepository designationRepository) {
		this.designationRepository = designationRepository;
	}
	
	@Transactional
	public DesignationResponseDto createDesignation(DesignationRequestDto request) {
		
		Designation designation = DesignationMapper.toEntity(request);
		designation.setIsActive(true);
		
		return DesignationMapper.toDto(designationRepository.save(designation));
	}
	
	public DesignationResponseDto getDesignationById(UUID id) {
		
		Designation designation = designationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Designation", id));
		
		return DesignationMapper.toDto(designation);
	}
	
	@Transactional
	public DesignationResponseDto updateDesignation(UUID id, DesignationRequestDto dto) {
		
		Designation existing = designationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Designation", id));
		
		existing.setDesignationName(dto.getDesignationName());
		
		return DesignationMapper.toDto(designationRepository.save(existing));
	}
	
	public List<DesignationResponseDto> getAllActiveDesignations() {
		return designationRepository.findByIsActiveTrue()
				.stream()
				.map(DesignationMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<DesignationResponseDto> getAllDesignations() {
		return designationRepository.findAll()
				.stream()
				.map(DesignationMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteDesignationById(UUID id) {
		Designation existing = designationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Designation", id));
		existing.setIsActive(false);
	}
}

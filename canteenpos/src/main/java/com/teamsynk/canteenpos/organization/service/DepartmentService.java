package com.teamsynk.canteenpos.organization.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.organization.dto.request.DepartmentRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.DepartmentResponseDto;
import com.teamsynk.canteenpos.organization.entity.Department;
import com.teamsynk.canteenpos.organization.mapper.DepartmentMapper;
import com.teamsynk.canteenpos.organization.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	@Transactional
	public DepartmentResponseDto createDepartment(DepartmentRequestDto request) {
		
		Department response = DepartmentMapper.toEntity(request);
		response.setIsActive(true);
		
		return DepartmentMapper.toDto(departmentRepository.save(response));
	}
	
	public DepartmentResponseDto getDepartmentById(UUID id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department", id));
		
		return DepartmentMapper.toDto(department);
	}
	
	@Transactional
	public DepartmentResponseDto updateDepartment(UUID id, DepartmentRequestDto dto) {
		Department existing = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department", id));
		existing.setDepartmentName(dto.getDepartmentName());
		existing.setDepartmentCode(dto.getDepartmentCode());
		
		return DepartmentMapper.toDto(departmentRepository.save(existing));
	}
	
	public List<DepartmentResponseDto> getAllActiveDepartments() {
		return departmentRepository.findByIsActiveTrue()
				.stream()
				.map(DepartmentMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<DepartmentResponseDto> getAllDepartments() {
		return departmentRepository.findAll()
				.stream()
				.map(DepartmentMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteDepartmentById(UUID id) {
		Department existing = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department", id));
		existing.setIsActive(false);
		departmentRepository.save(existing);
	}

}

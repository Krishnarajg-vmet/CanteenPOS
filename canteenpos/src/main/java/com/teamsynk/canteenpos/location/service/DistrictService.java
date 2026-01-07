package com.teamsynk.canteenpos.location.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.DistrictRequestDto;
import com.teamsynk.canteenpos.location.dto.response.DistrictResponseDto;
import com.teamsynk.canteenpos.location.entity.District;
import com.teamsynk.canteenpos.location.entity.State;
import com.teamsynk.canteenpos.location.mapper.DistrictMapper;
import com.teamsynk.canteenpos.location.repository.DistrictRepository;
import com.teamsynk.canteenpos.location.repository.StateRepository;

@Service
public class DistrictService {
	
	private final DistrictRepository districtRepository;
	private final StateRepository stateRepository;
	
	DistrictService(DistrictRepository districtRepository, StateRepository stateRepository) {
		this.districtRepository = districtRepository;
		this.stateRepository = stateRepository;
	}
	
	@Transactional
	public DistrictResponseDto createDistrict(DistrictRequestDto dto) {
		State state = stateRepository.findById(dto.getStateId())
					.orElseThrow(() -> new ResourceNotFoundException("State", dto.getStateId()));
		
		District district = DistrictMapper.toEntity(dto, state);
		district.setIsActive(true);
		return DistrictMapper.toDto(districtRepository.save(district));
	}
	
	public DistrictResponseDto getDistrictById(UUID id) {
		District district = districtRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("District", id));
		return DistrictMapper.toDto(district);
	}
	
	@Transactional
	public DistrictResponseDto updateDistrict(UUID id, DistrictRequestDto dto) {
		District existing = districtRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("District", id));
		existing.setDistrictName(dto.getDistrictName());
		
		if(dto.getStateId() != null &&
				!dto.getStateId().equals(existing.getState().getId())) {
			State state = stateRepository.findById(dto.getStateId())
				.orElseThrow(() -> new ResourceNotFoundException("State", dto.getStateId()));
			existing.setState(state);
		}
		return DistrictMapper.toDto(districtRepository.save(existing));
	}
	
	public List<DistrictResponseDto> getAllActiveDistricts() {
		return districtRepository.findByIsActiveTrue()
				.stream()
				.map(DistrictMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<DistrictResponseDto> getAllDistricts() {
		return districtRepository.findAll()
				.stream()
				.map(DistrictMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteDistrictById(UUID id) {
		District existing = districtRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("District", id));
		existing.setIsActive(false);
		districtRepository.save(existing);
	}

}

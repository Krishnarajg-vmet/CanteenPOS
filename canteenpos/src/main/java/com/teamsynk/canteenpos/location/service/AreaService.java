package com.teamsynk.canteenpos.location.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.AreaRequestDto;
import com.teamsynk.canteenpos.location.dto.response.AreaResponseDto;
import com.teamsynk.canteenpos.location.entity.Area;
import com.teamsynk.canteenpos.location.entity.City;
import com.teamsynk.canteenpos.location.mapper.AreaMapper;
import com.teamsynk.canteenpos.location.repository.AreaRepository;
import com.teamsynk.canteenpos.location.repository.CityRepository;

@Service
public class AreaService {

	private final AreaRepository areaRepository;
	private final CityRepository cityRepository;
	
	AreaService(AreaRepository areaRepository, CityRepository cityRepository) {
		this.areaRepository = areaRepository;
		this.cityRepository = cityRepository;
	}
	
	@Transactional
	public AreaResponseDto createArea(AreaRequestDto dto) {
		City city = cityRepository.findById(dto.getCityId())
				.orElseThrow(() -> new ResourceNotFoundException("City", dto.getCityId()));
		
		Area area = AreaMapper.toEntity(dto, city);
		area.setIsActive(true);
		return AreaMapper.toDto(areaRepository.save(area));
	}
	
	public AreaResponseDto getAreaById(UUID id) {
		Area area = areaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Area", id));
		return AreaMapper.toDto(area);
	}
	
	@Transactional
	public AreaResponseDto updateArea(UUID id, AreaRequestDto dto) {
		Area existing = areaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Area", id));
		
		existing.setAreaName(dto.getAreaName());
		
		if(dto.getCityId() != null &&
				!dto.getCityId().equals(existing.getCity().getId())) {
			City city = cityRepository.findById(dto.getCityId())
					.orElseThrow(() -> new ResourceNotFoundException("City", dto.getCityId()));
			existing.setCity(city);
		}
		
		return AreaMapper.toDto(areaRepository.save(existing));
	}
	
	public List<AreaResponseDto> getAllActiveAreas() {
		return areaRepository.findByIsActiveTrue()
				.stream()
				.map(AreaMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<AreaResponseDto> getAllAreas() {
		return areaRepository.findAll()
				.stream()
				.map(AreaMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteAreaById(UUID id) {
		Area existing = areaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Area", id));
		existing.setIsActive(false);
		areaRepository.save(existing);
	}
}

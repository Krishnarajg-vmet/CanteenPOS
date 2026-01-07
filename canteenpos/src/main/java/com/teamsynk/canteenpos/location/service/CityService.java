package com.teamsynk.canteenpos.location.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.CityRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CityResponseDto;
import com.teamsynk.canteenpos.location.entity.City;
import com.teamsynk.canteenpos.location.entity.District;
import com.teamsynk.canteenpos.location.mapper.CityMapper;
import com.teamsynk.canteenpos.location.repository.CityRepository;
import com.teamsynk.canteenpos.location.repository.DistrictRepository;

@Service
public class CityService {
	
	private final CityRepository cityRepository;
	private final DistrictRepository districtRepository;
	
	CityService(CityRepository cityRepository, DistrictRepository districtRepository) {
		this.cityRepository = cityRepository;
		this.districtRepository = districtRepository;
	}
	
	@Transactional
	public CityResponseDto createCity(CityRequestDto dto) {
		District district = districtRepository.findById(dto.getDistrictId())
				.orElseThrow(() -> new ResourceNotFoundException("District", dto.getDistrictId()));
		
		City city = CityMapper.toEntity(dto, district);
		city.setIsActive(true);
		return CityMapper.toDto(cityRepository.save(city));
	}
	
	public CityResponseDto getCityById(UUID id) {
		City city = cityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("City", id));
		return CityMapper.toDto(city);
	}
	
	@Transactional
	public CityResponseDto updateCity(UUID id, CityRequestDto dto) {
		City existing = cityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("City", id));
		
		existing.setCityName(dto.getCityName());
		
		if(dto.getDistrictId() != null &&
				!dto.getDistrictId().equals(existing.getDistrict().getId())) {
			District district = districtRepository.findById(dto.getDistrictId())
					.orElseThrow(() -> new ResourceNotFoundException("District", dto.getDistrictId()));
			
			existing.setDistrict(district);
		}
		
		return CityMapper.toDto(cityRepository.save(existing));
	}
	
	public List<CityResponseDto> getAllActiveCities() {
		return cityRepository.findByIsActiveTrue()
				.stream()
				.map(CityMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<CityResponseDto> getAllCities() {
		return cityRepository.findAll()
				.stream()
				.map(CityMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteCityById(UUID id) {
		City existing = cityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("City", id));
		existing.setIsActive(false);
		cityRepository.save(existing);
	}

}

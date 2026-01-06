package com.teamsynk.canteenpos.location.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.CountryRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CountryResponseDto;
import com.teamsynk.canteenpos.location.entity.Country;

@Component
public class CountryMapper {
	
	public static Country toEntity(CountryRequestDto dto) {
		if(dto==null) {
			throw new ResourceNotFoundException("Country", "DTO is NULL");
		}
		
		Country country = new Country();
		country.setCountryName(dto.getCountryName());
		country.setCountryCode(dto.getCountryCode());
		country.setNationality(dto.getNationality());
		return country;
	}
	
	public static CountryResponseDto toDto(Country country) {
		if(country == null) {
			throw new ResourceNotFoundException("Country", "Entity with this ID is NULL");
		}
		return new CountryResponseDto(
				country.getId(),
		        country.getCountryName(),
		        country.getNationality(),
		        country.getCountryCode(),
		        country.getIsActive(),
		        country.getCreatedDt(),
		        country.getModifiedDt(),
		        country.getCreatedBy(),
		        country.getModifiedBy()
				);
	}

}

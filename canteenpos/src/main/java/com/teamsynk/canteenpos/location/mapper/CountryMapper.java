package com.teamsynk.canteenpos.location.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.location.dto.request.CountryRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CountryResponseDto;
import com.teamsynk.canteenpos.location.entity.Country;

@Component
public class CountryMapper {
	
	public static Country toEntity(CountryRequestDto dto) {
		Country country = new Country();
		country.setCountryName(dto.getCountryName());
		country.setCountryCode(dto.getCountryCode());
		country.setNationality(dto.getNationality());
		return country;
	}
	
	public static CountryResponseDto toDto(Country country) {
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

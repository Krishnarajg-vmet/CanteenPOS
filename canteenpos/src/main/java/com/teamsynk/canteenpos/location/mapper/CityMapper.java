package com.teamsynk.canteenpos.location.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.location.dto.request.CityRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CityResponseDto;
import com.teamsynk.canteenpos.location.entity.City;
import com.teamsynk.canteenpos.location.entity.District;

@Component
public class CityMapper {
	
	public static City toEntity(CityRequestDto dto, District district) {
		
		City city = new City();
		city.setCityName(dto.getCityName());
		city.setDistrict(district);
		
		return city;
	}
	
	public static CityResponseDto toDto(City city) {
				
		return new CityResponseDto(
				city.getId(),
				city.getCityName(),
				city.getDistrict().getId(),
				city.getDistrict().getDistrictName(),
				city.getIsActive(),
				city.getCreatedDt(),
				city.getModifiedDt(),
				city.getCreatedBy(),
				city.getModifiedBy()
				);
	}

}

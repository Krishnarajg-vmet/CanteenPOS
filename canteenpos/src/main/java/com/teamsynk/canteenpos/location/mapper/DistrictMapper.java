package com.teamsynk.canteenpos.location.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.location.dto.request.DistrictRequestDto;
import com.teamsynk.canteenpos.location.dto.response.DistrictResponseDto;
import com.teamsynk.canteenpos.location.entity.District;
import com.teamsynk.canteenpos.location.entity.State;

@Component
public class DistrictMapper {
	
	public static District toEntity(DistrictRequestDto dto, State state) {
		District district = new District();
		district.setDistrictName(dto.getDistrictName());
		district.setState(state);
		
		return district;
		
	}
	
	public static DistrictResponseDto toDto(District district) {
				
		return new DistrictResponseDto(
				district.getId(),
				district.getDistrictName(),
				district.getState().getId(),
				district.getState().getStateName(),
				district.getIsActive(),
				district.getCreatedDt(),
				district.getModifiedDt(),
				district.getCreatedBy(),
				district.getModifiedBy()
				);
	}

}

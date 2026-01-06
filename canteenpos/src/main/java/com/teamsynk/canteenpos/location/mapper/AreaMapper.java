package com.teamsynk.canteenpos.location.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.location.dto.request.AreaRequestDto;
import com.teamsynk.canteenpos.location.dto.response.AreaResponseDto;
import com.teamsynk.canteenpos.location.entity.Area;
import com.teamsynk.canteenpos.location.entity.City;

@Component
public class AreaMapper {
	
	public static Area toEntity(AreaRequestDto dto, City city) {
		Area area = new Area();
		area.setAreaName(dto.getAreaName());
		area.setCity(city);
		
		return area;
		
	}
	
	public static AreaResponseDto toDto(Area area) {
		return new AreaResponseDto(
				area.getId(),
				area.getAreaName(),
				area.getCity().getId(),
				area.getCity().getCityName(),
				area.getIsActive(),
				area.getCreatedDt(),
				area.getModifiedDt(),
				area.getCreatedBy(),
				area.getModifiedBy()
				);
	}

}

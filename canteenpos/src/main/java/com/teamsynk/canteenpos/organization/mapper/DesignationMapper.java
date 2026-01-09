package com.teamsynk.canteenpos.organization.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.organization.dto.request.DesignationRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.DesignationResponseDto;
import com.teamsynk.canteenpos.organization.entity.Designation;

@Component
public class DesignationMapper {
	
	public static Designation toEntity(DesignationRequestDto dto) {
		
		Designation designation = new Designation();
		designation.setDesignationName(dto.getDesignationName());
		
		return designation;
	}
	
	public static DesignationResponseDto toDto(Designation designation) {
		return new DesignationResponseDto(
				designation.getId(),
				designation.getDesignationName());
	}

}

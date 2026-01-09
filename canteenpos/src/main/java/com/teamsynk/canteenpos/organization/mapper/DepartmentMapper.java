package com.teamsynk.canteenpos.organization.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.organization.dto.request.DepartmentRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.DepartmentResponseDto;
import com.teamsynk.canteenpos.organization.entity.Department;

@Component
public class DepartmentMapper {
	
	public static Department toEntity(DepartmentRequestDto dto) {
		
		Department department = new Department();
		department.setDepartmentName(dto.getDepartmentName());
		department.setDepartmentCode(dto.getDepartmentCode());
		
		return department;
	}
	
	public static DepartmentResponseDto toDto(Department department) {
		
		return new DepartmentResponseDto(department.getId(),
				department.getDepartmentName(),
				department.getDepartmentCode());
	}

}

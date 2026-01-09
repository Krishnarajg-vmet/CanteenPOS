package com.teamsynk.canteenpos.organization.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.organization.dto.request.EmployeeRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.EmployeeResponseDto;
import com.teamsynk.canteenpos.organization.entity.Branch;
import com.teamsynk.canteenpos.organization.entity.Designation;
import com.teamsynk.canteenpos.organization.entity.Employee;

@Component
public class EmployeeMapper {
	
	public static Employee toEntity(EmployeeRequestDto dto, Branch branch, Designation designation) {
		
		Employee employee = new Employee();
		
		employee.setTitle(dto.getTitle());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDob(dto.getDob());
        employee.setGender(dto.getGender());
        employee.setMaritalStatus(dto.getMaritalStatus());
        employee.setBloodGroup(dto.getBloodGroup());
        employee.setEmployeeType(dto.getEmployeeType());
        employee.setEmployeeCategory(dto.getEmployeeCategory());
        employee.setAddress(dto.getAddress());
        employee.setMblNumber(dto.getMblNumber());
        employee.setQualification(dto.getQualification());

        employee.setDesignation(designation);
        employee.setHomeBranch(branch);
		
		return employee;
	}
	
	public static EmployeeResponseDto toDto(Employee employee) {
		return new EmployeeResponseDto(
				employee.getId(),
				employee.getTitle(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmployeeCode(),
				employee.getDob(),
				employee.getGender(),
				employee.getMaritalStatus(),
				employee.getBloodGroup(),
				employee.getEmployeeType(),
				employee.getEmployeeCategory(),
				employee.getAddress(),
				employee.getDesignation().getId(),
				employee.getDesignation().getDesignationName(),
				employee.getMblNumber(),
				employee.getQualification(),
				employee.getHomeBranch().getId(),
				employee.getHomeBranch().getBranchName());
	}

}

package com.teamsynk.canteenpos.user.dto.response;

import java.util.Set;
import java.util.UUID;

import com.teamsynk.canteenpos.organization.dto.response.BranchResponseDto;
import com.teamsynk.canteenpos.organization.dto.response.DepartmentResponseDto;

public class UserResponseDto {
	
	private UUID id;
    private String username;
    private Boolean passwordResetRequired;
    
    private UUID employeeId;
    private String employeeName;
    private String employeeCode;
    
    private Set<RoleResponseDto> roles;
    private Set<BranchResponseDto> branches;
    private Set<DepartmentResponseDto> departments;
    
	public UserResponseDto(UUID id, String username, Boolean passwordResetRequired, UUID employeeId,
			String employeeName, String employeeCode, Set<RoleResponseDto> roles, Set<BranchResponseDto> branches,
			Set<DepartmentResponseDto> departments) {
		this.id = id;
		this.username = username;
		this.passwordResetRequired = passwordResetRequired;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.roles = roles;
		this.branches = branches;
		this.departments = departments;
	}
	public UUID getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public Boolean getPasswordResetRequired() {
		return passwordResetRequired;
	}
	public UUID getEmployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public Set<RoleResponseDto> getRoles() {
		return roles;
	}
	public Set<BranchResponseDto> getBranches() {
		return branches;
	}
	public Set<DepartmentResponseDto> getDepartments() {
		return departments;
	}

}

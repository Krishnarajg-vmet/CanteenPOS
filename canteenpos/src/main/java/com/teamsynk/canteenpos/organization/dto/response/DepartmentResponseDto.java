package com.teamsynk.canteenpos.organization.dto.response;

import java.util.UUID;

public class DepartmentResponseDto {

	private UUID id;
	private String departmentName;
	private String departmentCode;
	
	public DepartmentResponseDto(UUID id, String departmentName, String departmentCode) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
	}
	public UUID getId() {
		return id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
		
}

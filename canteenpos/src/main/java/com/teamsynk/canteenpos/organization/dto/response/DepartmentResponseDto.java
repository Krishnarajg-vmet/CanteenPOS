package com.teamsynk.canteenpos.organization.dto.response;

import java.time.Instant;
import java.util.UUID;

public class DepartmentResponseDto {

	private UUID id;
	private String departmentName;
	private String departmentCode;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
	
	public DepartmentResponseDto(UUID id, String departmentName, String departmentCode, Boolean isActive,
			Instant createdDt, Instant modifiedDt, UUID createdBy, UUID modifiedBy) {
		this.id = id;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
		this.isActive = isActive;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public Instant getCreatedDt() {
		return createdDt;
	}
	public Instant getModifiedDt() {
		return modifiedDt;
	}
	public UUID getCreatedBy() {
		return createdBy;
	}
	public UUID getModifiedBy() {
		return modifiedBy;
	}
		
}

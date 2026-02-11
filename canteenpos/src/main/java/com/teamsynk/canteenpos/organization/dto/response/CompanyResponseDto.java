package com.teamsynk.canteenpos.organization.dto.response;

import java.time.Instant;
import java.util.UUID;

public class CompanyResponseDto {

	private UUID id;
	private String companyName;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
	
	public CompanyResponseDto(UUID id, String companyName, Boolean isActive, Instant createdDt, Instant modifiedDt,
			UUID createdBy, UUID modifiedBy) {
		this.id = id;
		this.companyName = companyName;
		this.isActive = isActive;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	public UUID getId() {
		return id;
	}
	public String getCompanyName() {
		return companyName;
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

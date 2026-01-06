package com.teamsynk.canteenpos.location.dto.response;

import java.time.Instant;
import java.util.UUID;

public class StateResponseDto {
	
	private UUID id;
	private String stateName;
	private UUID countryId;
	private String countryName;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
	
	public StateResponseDto(UUID id, String stateName, UUID countryId, String countryName,Boolean isActive, Instant createdDt,
			Instant modifiedDt, UUID createdBy, UUID modifiedBy) {
		super();
		this.id = id;
		this.stateName = stateName;
		this.countryId = countryId;
		this.countryName = countryName;
		this.isActive = isActive;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	
	public UUID getId() {
		return id;
	}
	public String getStateName() {
		return stateName;
	}
	public UUID getCountryId() {
		return countryId;
	}
	public String getCountryName() {
		return countryName;
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

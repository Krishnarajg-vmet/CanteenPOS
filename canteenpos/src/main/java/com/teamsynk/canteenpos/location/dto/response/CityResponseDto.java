package com.teamsynk.canteenpos.location.dto.response;

import java.time.Instant;
import java.util.UUID;

public class CityResponseDto {
	
	private UUID id;
	private String cityName;
	private UUID districtId;
	private String districtName;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
		
	public CityResponseDto(UUID id, String cityName, UUID districtId, String districtName, Boolean isActive, Instant createdDt,
			Instant modifiedDt, UUID createdBy, UUID modifiedBy) {
		super();
		this.id = id;
		this.cityName = cityName;
		this.districtId = districtId;
		this.districtName = districtName;
		this.isActive = isActive;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	public UUID getId() {
		return id;
	}
	public String getCityName() {
		return cityName;
	}
	public UUID getDistrictId() {
		return districtId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public String getDistrictName() {
		return districtName;
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

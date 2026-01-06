package com.teamsynk.canteenpos.location.dto.response;

import java.time.Instant;
import java.util.UUID;

public class AreaResponseDto {
	
	private UUID id;
	private String areaName;
	private UUID cityId;
	private String cityName;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
	
	public AreaResponseDto(UUID id, String areaName, UUID cityId, String cityName, Boolean isActive, Instant createdDt,
			Instant modifiedDt, UUID createdBy, UUID modifiedBy) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.cityId = cityId;
		this.cityName = cityName;
		this.isActive = isActive;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	public UUID getId() {
		return id;
	}
	public String getAreaName() {
		return areaName;
	}
	public UUID getCityId() {
		return cityId;
	}
	public String getCityName() {
		return cityName;
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

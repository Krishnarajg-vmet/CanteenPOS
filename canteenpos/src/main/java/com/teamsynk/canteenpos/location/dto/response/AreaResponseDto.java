package com.teamsynk.canteenpos.location.dto.response;

import java.time.Instant;
import java.util.UUID;

public class AreaResponseDto {
	
	private UUID id;
	private String areaName;
	private String pincode;
	private UUID cityId;
	private String cityName;
	private UUID districtId;
	private String districtName;
	private UUID stateId;
	private String stateName;
	private UUID countryId;
	private String countryName;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
	
	public AreaResponseDto(UUID id, String areaName, String pincode, UUID cityId, String cityName, UUID districId, String districtName, UUID stateId, String stateName, UUID countryId, String countryName, Boolean isActive, Instant createdDt,
			Instant modifiedDt, UUID createdBy, UUID modifiedBy) {
		this.id = id;
		this.areaName = areaName;
		this.pincode = pincode;
		this.cityId = cityId;
		this.cityName = cityName;
		this.districtId = districId;
		this.districtName = districtName;
		this.stateId = stateId;
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
	public UUID getDistrictId() {
		return districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public UUID getStateId() {
		return stateId;
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
	public String getPincode() {
		return pincode;
	}
}

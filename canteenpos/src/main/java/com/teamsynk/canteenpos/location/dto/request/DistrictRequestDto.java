package com.teamsynk.canteenpos.location.dto.request;

import java.util.UUID;

public class DistrictRequestDto {
	
	private String districtName;
	private UUID stateId;
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public UUID getStateId() {
		return stateId;
	}
	public void setStateId(UUID stateId) {
		this.stateId = stateId;
	}

}

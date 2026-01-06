package com.teamsynk.canteenpos.location.dto.request;

import java.util.UUID;

public class AreaRequestDto {
	
	private String areaName;
	private UUID cityId;
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public UUID getCityId() {
		return cityId;
	}
	public void setCityId(UUID cityId) {
		this.cityId = cityId;
	}	

}

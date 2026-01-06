package com.teamsynk.canteenpos.location.dto.request;

import java.util.UUID;

public class CityRequestDto {
	
	private String cityName;
	private UUID districtId;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public UUID getDistrictId() {
		return districtId;
	}
	public void setDistrictId(UUID districtId) {
		this.districtId = districtId;
	}

}

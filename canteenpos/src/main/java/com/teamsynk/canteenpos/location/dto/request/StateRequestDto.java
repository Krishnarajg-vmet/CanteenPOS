package com.teamsynk.canteenpos.location.dto.request;

import java.util.UUID;

public class StateRequestDto {
	
	private String stateName;
	private UUID countryId;
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public UUID getCountryId() {
		return countryId;
	}
	public void setCountryId(UUID countryId) {
		this.countryId = countryId;
	}

}

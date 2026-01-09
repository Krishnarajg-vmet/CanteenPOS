package com.teamsynk.canteenpos.organization.dto.response;

import java.util.UUID;

public class CompanyResponseDto {

	private UUID id;
	private String companyName;
	
	public CompanyResponseDto(UUID id, String companyName) {
		this.id = id;
		this.companyName = companyName;
	}
	
	public UUID getId() {
		return id;
	}
	public String getCompanyName() {
		return companyName;
	}
		
	
}

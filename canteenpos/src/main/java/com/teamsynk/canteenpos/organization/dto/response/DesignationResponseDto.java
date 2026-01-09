package com.teamsynk.canteenpos.organization.dto.response;

import java.util.UUID;

public class DesignationResponseDto {

	private UUID id;
	private String designationName;
	
	public DesignationResponseDto(UUID id, String designationName) {
		super();
		this.id = id;
		this.designationName = designationName;
	}
	public UUID getId() {
		return id;
	}
	public String getDesignationName() {
		return designationName;
	}
}

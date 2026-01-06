package com.teamsynk.canteenpos.location.dto.response;

import java.time.Instant;
import java.util.UUID;

public class CountryResponseDto {
	
	private UUID id;
	private String countryName;
	private String nationality;
	private String countryCode;
	private Boolean isActive;
	private Instant createdDt;
	private Instant modifiedDt;
	private UUID createdBy;
	private UUID modifiedBy;
	
	public CountryResponseDto(UUID id,
		 String countryName,
		 String nationality,
		 String countryCode,
		 Boolean isActive,
		 Instant createdDt,
		 Instant modifiedDt,
		 UUID createdBy,
		 UUID modifiedBy) {
		this.id = id;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.nationality = nationality;
		this.isActive = isActive;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public UUID getId() {
		return id;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getNationality() {
		return nationality;
	}

	public String getCountryCode() {
		return countryCode;
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

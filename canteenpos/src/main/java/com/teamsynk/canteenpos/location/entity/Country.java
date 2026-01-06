package com.teamsynk.canteenpos.location.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="country")
public class Country extends BaseEntity{
	
	@Id
	@Column(name="country_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="country_name", nullable = false, unique = true)
	private String countryName;
	
	@Column(name="nationality", nullable = false, unique = true)
	private String nationality;
	
	@Column(name="country_code", nullable = false, unique = true)
	private String countryCode;

	public UUID getId() {
		return id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}

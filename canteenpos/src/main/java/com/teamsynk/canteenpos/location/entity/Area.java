package com.teamsynk.canteenpos.location.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="area")
public class Area extends BaseEntity {
	
	@Id
	@Column(name="area_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="area_name", nullable = false, unique = true)
	private String areaName;
	
	@Column(name="pincode", nullable = false)
	private String pincode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="city_id", nullable = false)
	private City city;

	public UUID getId() {
		return id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}

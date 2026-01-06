package com.teamsynk.canteenpos.location.entity;

import jakarta.persistence.*;

@Embeddable
public class Address {
	
	@Column(name="address", nullable = false)
	private String address;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area_id", nullable = false)
	private Area area;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}

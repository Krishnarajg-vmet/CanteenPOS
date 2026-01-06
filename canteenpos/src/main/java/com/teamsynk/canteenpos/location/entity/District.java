package com.teamsynk.canteenpos.location.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="district")
public class District extends BaseEntity {
	
	@Id
	@Column(name="district_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="district_name", nullable = false, unique = true)
	private String districtName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="state_id", nullable = false)
	private State state;
	
	protected District() {
	}

	public UUID getId() {
		return id;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}

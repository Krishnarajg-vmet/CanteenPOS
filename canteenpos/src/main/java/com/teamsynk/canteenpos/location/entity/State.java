package com.teamsynk.canteenpos.location.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="state")
public class State extends BaseEntity {
	
	@Id
	@Column(name="state_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="state_name", nullable = false, unique = true)
	private String stateName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id", nullable = false)
	private Country country;

	public UUID getId() {
		return id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	

}

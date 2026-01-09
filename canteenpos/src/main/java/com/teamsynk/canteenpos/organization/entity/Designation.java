package com.teamsynk.canteenpos.organization.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="designation")
public class Designation extends BaseEntity{
	
	@Id
	@Column(name="designation_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="designation_name", nullable = false, unique = true)
	private String designationName;

	public UUID getId() {
		return id;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	

}

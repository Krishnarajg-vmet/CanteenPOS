package com.teamsynk.canteenpos.organization.entity;

import java.util.Set;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.user.entity.User;

import jakarta.persistence.*;

@Entity
@Table(name="department")
public class Department extends BaseEntity{
	
	@Id
	@Column(name="department_id", nullable = false, updatable = false)
	private UUID id = IdGenerator.newUUID();
	
	@Column(name="department_name", nullable = false, unique = true)
	private String departmentName;
	
	@Column(name="department_code", nullable = false, unique = true)
	private String departmentCode;

	public UUID getId() {
		return id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

}

package com.teamsynk.canteenpos.organization.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="branch", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branch_name", "company_id"}),
        @UniqueConstraint(columnNames = {"branch_code", "company_id"})})
public class Branch extends BaseEntity {
	
	@Id
	@Column(name="branch_id", nullable = false, updatable = false)
	private UUID id  = IdGenerator.newUUID();
	
	@Column(name="branch_name", nullable = false)
	private String branchName;
	
	@Column(name="branch_code", nullable = false)
	private String branchCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable = false)
	private Company company;

	public UUID getId() {
		return id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}

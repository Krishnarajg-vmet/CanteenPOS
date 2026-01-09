package com.teamsynk.canteenpos.organization.dto.request;

import java.util.UUID;

public class BranchRequestDto {

	private String branchName;
	private String branchCode;
	private UUID companyId;
	
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
	public UUID getCompanyId() {
		return companyId;
	}
	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}
	
}

package com.teamsynk.canteenpos.organization.dto.response;

import java.util.UUID;

public class BranchResponseDto {

	private UUID id;
	private String branchName;
	private String branchCode;
	private UUID companyId;
	private String companyName;
	
	public BranchResponseDto(UUID id, String branchName, String branchCode, UUID companyId, String companyName) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	public UUID getId() {
		return id;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public UUID getCompanyId() {
		return companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
}

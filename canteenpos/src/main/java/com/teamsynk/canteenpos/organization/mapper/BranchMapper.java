package com.teamsynk.canteenpos.organization.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.organization.dto.request.BranchRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.BranchResponseDto;
import com.teamsynk.canteenpos.organization.entity.Branch;
import com.teamsynk.canteenpos.organization.entity.Company;

@Component
public class BranchMapper {
	
	public static Branch toEntity(BranchRequestDto dto, Company company) {
		
		Branch branch = new Branch();
		branch.setBranchName(dto.getBranchName());
		branch.setBranchCode(dto.getBranchCode());
		branch.setCompany(company);
		
		return branch;
	}
	
	public static BranchResponseDto toDto(Branch branch) {
		return new BranchResponseDto(
				branch.getId(),
				branch.getBranchName(),
				branch.getBranchCode(),
				branch.getCompany().getId(),
				branch.getCompany().getCompanyName());
	}

}

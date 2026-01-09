package com.teamsynk.canteenpos.organization.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.organization.dto.request.CompanyRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.CompanyResponseDto;
import com.teamsynk.canteenpos.organization.entity.Company;

@Component
public class CompanyMapper {
	
	public static Company toEntity(CompanyRequestDto dto) {
		
		Company company = new Company();
		company.setCompanyName(dto.getCompanyName());
		
		return company;
	}
	
	public static CompanyResponseDto toDto(Company company) {
		return new CompanyResponseDto(company.getId(), company.getCompanyName());
	}

}

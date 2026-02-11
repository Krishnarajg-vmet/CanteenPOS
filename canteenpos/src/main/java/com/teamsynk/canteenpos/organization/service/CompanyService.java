package com.teamsynk.canteenpos.organization.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.dto.request.CompanyRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.CompanyResponseDto;
import com.teamsynk.canteenpos.organization.entity.Company;
import com.teamsynk.canteenpos.organization.mapper.CompanyMapper;
import com.teamsynk.canteenpos.organization.repository.CompanyRepository;


@Service
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Transactional
	public CompanyResponseDto createCompany(CompanyRequestDto request) {
		Company company = CompanyMapper.toEntity(request);
		company.setIsActive(true);
		company.setCreatedBy(IdGenerator.newUUID());
		return CompanyMapper.toDto(companyRepository.save(company));
	}
	
	public CompanyResponseDto getCompanyById(UUID id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company", id));
		return CompanyMapper.toDto(company);
	}
	
	@Transactional
	public CompanyResponseDto updateCompany(UUID id, CompanyRequestDto dto) {
		Company existing = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company", id));
		
		existing.setCompanyName(dto.getCompanyName());
		existing.setModifiedBy(IdGenerator.newUUID());
		return CompanyMapper.toDto(companyRepository.save(existing));
	}
	
	@Transactional(readOnly = true)
	public List<CompanyResponseDto> getAllActiveCompanies(){
		return companyRepository.findByIsActiveTrue()
				.stream()
				.map(CompanyMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<CompanyResponseDto> getAllCompanies(){
		return companyRepository.findAll()
				.stream()
				.map(CompanyMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteCompany(UUID id) {
		Company existing = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company", id));
		existing.setIsActive(false);
		existing.setModifiedBy(IdGenerator.newUUID());
		companyRepository.save(existing);
	}
	
	@Transactional
	public void acticateCompanyById(UUID id) {
		Company existing = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company", id));
		existing.setIsActive(true);
		existing.setModifiedBy(IdGenerator.newUUID());
		companyRepository.save(existing);
	}

}

package com.teamsynk.canteenpos.organization.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.dto.request.BranchRequestDto;
import com.teamsynk.canteenpos.organization.dto.response.BranchResponseDto;
import com.teamsynk.canteenpos.organization.entity.Branch;
import com.teamsynk.canteenpos.organization.entity.Company;
import com.teamsynk.canteenpos.organization.mapper.BranchMapper;
import com.teamsynk.canteenpos.organization.repository.BranchRepository;
import com.teamsynk.canteenpos.organization.repository.CompanyRepository;

@Service
public class BranchService {
	
	private final BranchRepository branchRepository;
	private final CompanyRepository companyRepository;
	
	public BranchService(BranchRepository branchRepository, CompanyRepository companyRepository) {
		this.branchRepository = branchRepository;
		this.companyRepository = companyRepository;
	}
	
	@Transactional
	public BranchResponseDto createBranch(BranchRequestDto request) {
		Company company = companyRepository.findById(request.getCompanyId())
				.orElseThrow(() -> new ResourceNotFoundException("Company", request.getCompanyId()));
		
		Branch branch = BranchMapper.toEntity(request, company);
		branch.setIsActive(true);
		branch.setCreatedBy(IdGenerator.newUUID());
		return BranchMapper.toDto(branchRepository.save(branch));
	}
	
	public BranchResponseDto getBranchById(UUID id) {
		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", id));
		return BranchMapper.toDto(branch);
	}
	
	@Transactional
	public BranchResponseDto updateBranch(UUID id, BranchRequestDto dto) {
		Branch existing = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", id));
		existing.setBranchName(dto.getBranchName());
		existing.setBranchCode(dto.getBranchCode());
		existing.setModifiedBy(IdGenerator.newUUID());
		if(dto.getCompanyId() != null &&
				!dto.getCompanyId().equals(existing.getCompany().getId())) {
			Company company = companyRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Company", dto.getCompanyId()));
			existing.setCompany(company);
		}
		return BranchMapper.toDto(branchRepository.save(existing));
	}
	
	@Transactional(readOnly = true)
	public List<BranchResponseDto> getAllActiveBranches() {
		return branchRepository.findByIsActiveTrue()
				.stream()
				.map(BranchMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<BranchResponseDto> getAllBranches() {
		return branchRepository.findAll()
				.stream()
				.map(BranchMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteBranchById(UUID id) {
		Branch existing = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", id));
		existing.setIsActive(false);
		existing.setModifiedBy(IdGenerator.newUUID());
		branchRepository.save(existing);
	}
	
	@Transactional
	public void activateBranchById(UUID id) {
		Branch existing = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", id));
		existing.setIsActive(true);
		existing.setModifiedBy(IdGenerator.newUUID());
		branchRepository.save(existing);
	}
}

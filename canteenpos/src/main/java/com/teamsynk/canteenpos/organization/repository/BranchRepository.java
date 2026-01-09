package com.teamsynk.canteenpos.organization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.organization.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID>{
	
	List<Branch> findByIsActiveTrue();
	Optional<Branch> findByBranchNameIgnoreCase(String branchName);
	Optional<Branch> findByBranchCodeIgnoreCase(String branchCode);
	List<Branch> findByCompanyCompanyNameIgnoreCase(String companyName);
	boolean existsByBranchNameIgnoreCase(String branchName);

}

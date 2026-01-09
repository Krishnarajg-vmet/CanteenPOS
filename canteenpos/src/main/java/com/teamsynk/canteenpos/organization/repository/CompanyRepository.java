package com.teamsynk.canteenpos.organization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.organization.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
	
	List<Company> findByIsActiveTrue();
	Optional<Company> findByCompanyNameIgnoreCase(String companyName);
	boolean existsByCompanyNameIgnoreCase(String companyName);

}

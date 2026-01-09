package com.teamsynk.canteenpos.organization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.organization.entity.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, UUID> {
	
	List<Designation> findByIsActiveTrue();
	Optional<Designation> findByDesignationNameIgnoreCase(String designationName);
	boolean existsByDesignationNameIgnoreCase(String designationName);

}

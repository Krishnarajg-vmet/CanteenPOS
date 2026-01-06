package com.teamsynk.canteenpos.location.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.location.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
	
	List<District> findByIsActiveTrue();
	Optional<District> findByDistrictNameIgnoreCase(String districtName);
	List<District> findByStateStateNameIgnoreCase(String stateName);
	boolean existsByDistrictNameIgnoreCase(String districtName);

}

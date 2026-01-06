package com.teamsynk.canteenpos.location.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.location.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {

	List<Area> findByIsActiveTrue();
	Optional<Area> findByAreaNameIgnoreCase(String areaName);
	List<Area> findByCityCityNameIgnoreCase(String cityName);
	boolean existsByAreaNameIgnoreCase(String areaName);
	
}

package com.teamsynk.canteenpos.location.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.location.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
	
	List<City> findByIsActiveTrue();
	Optional<City> findByCityNameIgnoreCase(String cityName);
	List<City> findByDistrictDistrictNameIgnoreCase(String districtName);
	boolean existsByCityNameIgnoreCase(String cityName);

}

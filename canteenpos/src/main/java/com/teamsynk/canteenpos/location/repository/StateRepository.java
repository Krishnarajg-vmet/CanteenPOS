package com.teamsynk.canteenpos.location.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.location.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, UUID>{
	
	List<State> findByIsActiveTrue();
	Optional<State> findByStateNameIgnoreCase(String stateName);
	List<State> findByCountryCountryNameIgnoreCase(String countryName);
	boolean existsByStateNameIgnoreCase(String stateName);

}

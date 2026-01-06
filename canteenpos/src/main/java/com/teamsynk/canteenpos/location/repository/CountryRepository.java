package com.teamsynk.canteenpos.location.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.location.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

    List<Country> findByIsActiveTrue();
    Optional<Country> findByCountryNameIgnoreCase(String countryName);
    Optional<Country> findByCountryCode(String countryCode);
    boolean existsByCountryNameIgnoreCase(String countryName);
    boolean existsByCountryCode(String countryCode);
}

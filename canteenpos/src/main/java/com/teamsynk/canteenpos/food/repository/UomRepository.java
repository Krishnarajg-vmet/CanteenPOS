package com.teamsynk.canteenpos.food.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.food.entity.Uom;

@Repository
public interface UomRepository extends JpaRepository<Uom, UUID> {

    Optional<Uom> findByUomCodeAndIsActiveTrue(String uomCode);

    Optional<Uom> findByIsBaseTrueAndUomTypeAndIsActiveTrue(
            Enum<?> uomType);
}
package com.teamsynk.canteenpos.food.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.food.entity.UomConversion;

@Repository
public interface UomConversionRepository extends JpaRepository<UomConversion, UUID> {

    @Query("""
        select uc
        from UomConversion uc
        where uc.fromUom.id = :fromUomId
          and uc.toUom.id = :toUomId
          and :today between uc.effectiveFrom
                          and coalesce(uc.effectiveTo, :today)
          and uc.isActive = true
    """)
    Optional<UomConversion> findActiveConversion(
            UUID fromUomId,
            UUID toUomId,
            LocalDate today);
}

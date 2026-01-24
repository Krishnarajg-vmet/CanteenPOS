package com.teamsynk.canteenpos.food.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.food.entity.FoodPrice;

@Repository
public interface FoodPriceRepository extends JpaRepository<FoodPrice, UUID> {

    @Query("""
        select fp
        from FoodPrice fp
        where fp.food.id = :foodId
          and fp.location.id = :locationId
          and fp.isCurrent = true
          and fp.isActive = true
    """)
    Optional<FoodPrice> findCurrentPrice(
            UUID foodId,
            UUID locationId);

    @Query("""
        select fp
        from FoodPrice fp
        where fp.food.id = :foodId
          and fp.location.id = :locationId
          and :today between fp.effectiveFrom
                          and coalesce(fp.effectiveTo, :today)
          and fp.isActive = true
        order by fp.effectiveFrom desc
    """)
    Optional<FoodPrice> findPriceForDate(
            UUID foodId,
            UUID locationId,
            LocalDate today);

    List<FoodPrice> findAllByFood_IdAndLocation_IdAndIsActiveTrue(
            UUID foodId, UUID locationId);
}
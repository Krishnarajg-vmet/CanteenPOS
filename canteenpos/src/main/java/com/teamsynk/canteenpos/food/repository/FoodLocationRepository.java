package com.teamsynk.canteenpos.food.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.food.entity.FoodLocation;

@Repository
public interface FoodLocationRepository extends JpaRepository<FoodLocation, UUID> {

    Optional<FoodLocation> findByFood_IdAndLocation_IdAndIsActiveTrue(
            UUID foodId, UUID locationId);

    List<FoodLocation> findAllByLocation_IdAndIsSellableTrueAndIsActiveTrue(
            UUID locationId);

    @Query("""
        select fl
        from FoodLocation fl
        where fl.food.id = :foodId
          and fl.location.id = :locationId
          and fl.isSellable = true
          and fl.isActive = true
          and :today between fl.effectiveFrom
                          and coalesce(fl.effectiveTo, :today)
    """)
    Optional<FoodLocation> findSellableFoodForDate(
            UUID foodId,
            UUID locationId,
            LocalDate today);

}

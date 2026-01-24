package com.teamsynk.canteenpos.food.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.food.entity.FoodType;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, UUID> {

    Optional<FoodType> findByFoodTypeCode(String code);

    Optional<FoodType> findByFoodTypeName(String name);

    boolean existsByFoodTypeCode(String code);

    boolean existsByFoodTypeName(String name);

    List<FoodType> findAllByIsActiveTrue();
}

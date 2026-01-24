package com.teamsynk.canteenpos.food.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.food.entity.FoodCategory;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, UUID> {

    Optional<FoodCategory> findByFoodCategoryCodeAndIsActiveTrue(String foodCategoryCode);

    List<FoodCategory> findAllByParentCategory_IdAndIsActiveTrue(UUID parentCategoryId);

    List<FoodCategory> findAllByIsActiveTrueOrderBySortOrderAsc();
}
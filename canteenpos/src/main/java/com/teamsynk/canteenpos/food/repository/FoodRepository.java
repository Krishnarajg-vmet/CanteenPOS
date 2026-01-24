package com.teamsynk.canteenpos.food.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.common.enums.FoodStatus;
import com.teamsynk.canteenpos.food.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

    Optional<Food> findByFoodCodeAndIsActiveTrue(String foodCode);
    
    List<Food> findAllByIsActiveTrue();
    
    Optional<Food> findByFoodNameAndIsActiveTrue(String foodName);

    boolean existsByFoodNameAndFoodCategory_IdAndIsActiveTrue(
            String foodName, UUID foodCategoryId);

    List<Food> findAllByFoodCategory_IdAndFoodStatusAndIsActiveTrue(
            UUID foodCategoryId, FoodStatus foodStatus);

    @Query("""
        select f
        from Food f
        where f.foodStatus = :status
          and f.isActive = true
        order by f.foodName
    """)
    List<Food> findAllActiveByStatus(FoodStatus status);
}


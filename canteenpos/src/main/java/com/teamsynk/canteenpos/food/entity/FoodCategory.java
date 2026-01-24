package com.teamsynk.canteenpos.food.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(
    name = "food_category",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"category_code"})
    }
)
public class FoodCategory extends BaseEntity {

    @Id
    @Column(name = "food_category_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "category_code", nullable = false, length = 50)
    private String foodCategoryCode;

    @Column(name = "category_name", nullable = false, length = 150)
    private String foodCategoryName;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private FoodCategory parentCategory;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
        }
    }

	public UUID getId() {
		return id;
	}

	public FoodCategory getParentCategory() {
		return parentCategory;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setParentCategory(FoodCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getFoodCategoryCode() {
		return foodCategoryCode;
	}

	public String getFoodCategoryName() {
		return foodCategoryName;
	}

	public void setFoodCategoryCode(String foodCategoryCode) {
		this.foodCategoryCode = foodCategoryCode;
	}

	public void setFoodCategoryName(String foodCategoryName) {
		this.foodCategoryName = foodCategoryName;
	}

}

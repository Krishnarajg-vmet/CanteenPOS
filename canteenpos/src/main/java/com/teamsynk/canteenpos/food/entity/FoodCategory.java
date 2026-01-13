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
    private String categoryCode;

    @Column(name = "category_name", nullable = false, length = 150)
    private String categoryName;

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

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public FoodCategory getParentCategory() {
		return parentCategory;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setParentCategory(FoodCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}

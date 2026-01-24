package com.teamsynk.canteenpos.food.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="food_type")
public class FoodType extends BaseEntity {

	@Id
	@Column(name="food_type_id", nullable = false, updatable = false)
    private UUID id;

	@Column(name="food_type_code", nullable = false, unique = true)
    private String foodTypeCode;
	
	@Column(name="food_type_name", nullable = false, unique = true)
    private String foodTypeName;

	@PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
        }
    }
	
	public UUID getId() {
		return id;
	}

	public String getFoodTypeCode() {
		return foodTypeCode;
	}

	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeCode(String foodTypeCode) {
		this.foodTypeCode = foodTypeCode;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
}

package com.teamsynk.canteenpos.food.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.FoodStatus;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="food",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"food_name", "food_category_id"})
				})
public class Food extends BaseEntity {
	
	@Id
	@Column(name="food_id", nullable = false, updatable = false)
	private UUID id;
	
	@Column(name="food_name", nullable = false)
	private String foodName;
	
	@Column(name="food_code", nullable = false, unique = true)
	private String foodCode;
	
	@Column(name="food_description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="food_category_id", nullable = false)
	private FoodCategory foodCategory;
	
	@ManyToOne
	@JoinColumn(name="food_type_id", nullable = false)
	private FoodType foodType;
	
	@Enumerated(EnumType.STRING)
	@Column(name="food_status", nullable = false)
	private FoodStatus foodStatus;
	
	@ManyToOne
	@JoinColumn(name="uom_id")
	private Uom uom;
    
    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
            if (foodStatus == null) foodStatus = FoodStatus.DRAFT;
        }
    }

	public UUID getId() {
		return id;
	}

	public String getFoodName() {
		return foodName;
	}

	public String getFoodCode() {
		return foodCode;
	}

	public String getDescription() {
		return description;
	}

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public FoodStatus getFoodStatus() {
		return foodStatus;
	}

	public Uom getUom() {
		return uom;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public void setFoodStatus(FoodStatus foodStatus) {
		this.foodStatus = foodStatus;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

}

package com.teamsynk.canteenpos.food.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.entity.Branch;

import jakarta.persistence.*;

@Entity
@Table(name="food_location",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"food_id", "location_id"})
		})
public class FoodLocation extends BaseEntity {
	
	@Id
	@Column(name="food_location_id", nullable = false, updatable = false)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="food_id", nullable = false)
	private Food food;
	
	@ManyToOne
	@JoinColumn(name="location_id", nullable = false)
	private Branch location;
	
	@Column(name="is_sellable", nullable = false)
	private Boolean isSellable = true;
	
	@Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

	public UUID getId() {
		return id;
	}

	public Food getFood() {
		return food;
	}

	public Branch getLocation() {
		return location;
	}

	public Boolean getIsSellable() {
		return isSellable;
	}

	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}

	public LocalDate getEffectiveTo() {
		return effectiveTo;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void setLocation(Branch location) {
		this.location = location;
	}

	public void setIsSellable(Boolean isSellable) {
		this.isSellable = isSellable;
	}

	public void setEffectiveFrom(LocalDate effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public void setEffectiveTo(LocalDate effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	
	@PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
        }
        if (isSellable == null) {
            isSellable = true;
        }
    }

}

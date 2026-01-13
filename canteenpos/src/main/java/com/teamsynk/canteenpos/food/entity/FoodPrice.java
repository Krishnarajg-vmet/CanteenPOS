package com.teamsynk.canteenpos.food.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.util.IdGenerator;
import com.teamsynk.canteenpos.organization.entity.Branch;

import jakarta.persistence.*;

@Entity
@Table(
    name = "food_price",
    indexes = {
        @Index(name = "idx_food_price_food_location", columnList = "food_id, location_id"),
        @Index(name = "idx_food_price_effective", columnList = "effective_from, effective_to")
    }
)
public class FoodPrice extends BaseEntity {

    @Id
    @Column(name = "food_price_id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Branch location;

    @Column(name = "price", nullable = false, precision = 15, scale = 4)
    private BigDecimal price;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "is_current", nullable = false)
    private Boolean isCurrent = true;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
        }
        if (isCurrent == null) {
            isCurrent = true;
        }
    }

	public UUID getId() {
		return id;
	}

	public Food getFood() {
		return food;
	}

	public Branch getLocation() {
		return location;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}

	public LocalDate getEffectiveTo() {
		return effectiveTo;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void setLocation(Branch location) {
		this.location = location;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setEffectiveFrom(LocalDate effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public void setEffectiveTo(LocalDate effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

}

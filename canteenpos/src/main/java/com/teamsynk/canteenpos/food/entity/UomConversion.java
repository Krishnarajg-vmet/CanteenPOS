package com.teamsynk.canteenpos.food.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.ConversionMethod;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(
    name = "uom_conversion",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"from_uom_id", "to_uom_id"})
    }
)
public class UomConversion extends BaseEntity {

    @Id
    @Column(name = "uom_conversion_id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_uom_id", nullable = false)
    private Uom fromUom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_uom_id", nullable = false)
    private Uom toUom;

    @Column(name = "conversion_factor", nullable = false, precision = 18, scale = 8)
    private BigDecimal conversionFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "conversion_method", nullable = false)
    private ConversionMethod conversionMethod;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
        }
    }

	public UUID getId() {
		return id;
	}

	public Uom getFromUom() {
		return fromUom;
	}

	public Uom getToUom() {
		return toUom;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public ConversionMethod getConversionMethod() {
		return conversionMethod;
	}

	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}

	public LocalDate getEffectiveTo() {
		return effectiveTo;
	}

	public void setFromUom(Uom fromUom) {
		this.fromUom = fromUom;
	}

	public void setToUom(Uom toUom) {
		this.toUom = toUom;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public void setConversionMethod(ConversionMethod conversionMethod) {
		this.conversionMethod = conversionMethod;
	}

	public void setEffectiveFrom(LocalDate effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public void setEffectiveTo(LocalDate effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
    
}

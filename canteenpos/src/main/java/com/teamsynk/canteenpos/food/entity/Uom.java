package com.teamsynk.canteenpos.food.entity;

import java.util.UUID;

import com.teamsynk.canteenpos.common.BaseEntity;
import com.teamsynk.canteenpos.common.enums.UomType;
import com.teamsynk.canteenpos.common.util.IdGenerator;

import jakarta.persistence.*;

@Entity
@Table(
    name = "uom",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uom_code"})
    }
)
public class Uom extends BaseEntity {

    @Id
    @Column(name = "uom_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "uom_code", nullable = false, length = 20)
    private String uomCode;

    @Column(name = "uom_name", nullable = false, length = 100)
    private String uomName;

    @Enumerated(EnumType.STRING)
    @Column(name = "uom_type", nullable = false, length = 20)
    private UomType uomType;

    @Column(name = "is_base", nullable = false)
    private Boolean isBase = false;

    @Column(name = "precision_value")
    private Integer precisionValue;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = IdGenerator.newUUID();
        }
        if (isBase == null) {
            isBase = false;
        }
    }

	public UUID getId() {
		return id;
	}

	public String getUomCode() {
		return uomCode;
	}

	public String getUomName() {
		return uomName;
	}

	public UomType getUomType() {
		return uomType;
	}

	public Boolean getIsBase() {
		return isBase;
	}

	public Integer getPrecisionValue() {
		return precisionValue;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public void setIsBase(Boolean isBase) {
		this.isBase = isBase;
	}

	public void setPrecisionValue(Integer precisionValue) {
		this.precisionValue = precisionValue;
	}

}

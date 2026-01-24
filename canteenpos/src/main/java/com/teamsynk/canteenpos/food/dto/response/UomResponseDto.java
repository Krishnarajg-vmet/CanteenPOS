package com.teamsynk.canteenpos.food.dto.response;

import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.UomType;

public class UomResponseDto {

    private UUID id;
    private String uomCode;
    private String uomName;
    private UomType uomType;
    private Boolean isBase;
    private Integer precisionValue;

    public UomResponseDto(UUID id, String uomCode, String uomName, UomType uomType, Boolean isBase,
			Integer precisionValue) {
		super();
		this.id = id;
		this.uomCode = uomCode;
		this.uomName = uomName;
		this.uomType = uomType;
		this.isBase = isBase;
		this.precisionValue = precisionValue;
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
}


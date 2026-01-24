package com.teamsynk.canteenpos.food.dto.request;

import com.teamsynk.canteenpos.common.enums.UomType;

public class UomRequestDto {

	private String uomCode;
	private String uomName;
	private UomType uomType;
	private Boolean isBase;
	private Integer precisionValue;
	
	
	public UomRequestDto(String uomCode, String uomName, UomType uomType, Boolean isBase, Integer precisionValue) {
		this.uomCode = uomCode;
		this.uomName = uomName;
		this.uomType = uomType;
		this.isBase = isBase;
		this.precisionValue = precisionValue;
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

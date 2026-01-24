package com.teamsynk.canteenpos.food.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.ConversionMethod;

public class UomConversionRequestDto {

	private UUID fromUomId;
	private UUID toUomId;
	private BigDecimal conversionFactor;
	private ConversionMethod conversionMethod;
	private LocalDate effectiveFrom;
	private LocalDate effectiveTo;
	
	public UomConversionRequestDto(UUID fromUomId, UUID toUomId, BigDecimal conversionFactor,
			ConversionMethod conversionMethod, LocalDate effectiveFrom, LocalDate effectiveTo) {
		super();
		this.fromUomId = fromUomId;
		this.toUomId = toUomId;
		this.conversionFactor = conversionFactor;
		this.conversionMethod = conversionMethod;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
	}
	public UUID getFromUomId() {
		return fromUomId;
	}
	public UUID getToUomId() {
		return toUomId;
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
}

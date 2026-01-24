package com.teamsynk.canteenpos.food.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.teamsynk.canteenpos.common.enums.ConversionMethod;

public class UomConversionResponseDto {

    private UUID id;

    private UUID fromUomId;
    private String fromUomCode;
    private String fromUomName;

    private UUID toUomId;
    private String toUomCode;
    private String toUomName;

    private BigDecimal conversionFactor;
    private ConversionMethod conversionMethod;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    
    public UomConversionResponseDto(UUID id, UUID fromUomId, String fromUomCode, String fromUomName, UUID toUomId,
			String toUomCode, String toUomName, BigDecimal conversionFactor, ConversionMethod conversionMethod,
			LocalDate effectiveFrom, LocalDate effectiveTo) {
		super();
		this.id = id;
		this.fromUomId = fromUomId;
		this.fromUomCode = fromUomCode;
		this.fromUomName = fromUomName;
		this.toUomId = toUomId;
		this.toUomCode = toUomCode;
		this.toUomName = toUomName;
		this.conversionFactor = conversionFactor;
		this.conversionMethod = conversionMethod;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
	}

	public UUID getId() {
        return id;
    }
    public UUID getFromUomId() {
        return fromUomId;
    }
    public String getFromUomCode() {
        return fromUomCode;
    }
    public String getFromUomName() {
        return fromUomName;
    }
    public UUID getToUomId() {
        return toUomId;
    }
    public String getToUomCode() {
        return toUomCode;
    }
    public String getToUomName() {
        return toUomName;
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


package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.food.dto.request.UomConversionRequestDto;
import com.teamsynk.canteenpos.food.dto.response.UomConversionResponseDto;
import com.teamsynk.canteenpos.food.entity.Uom;
import com.teamsynk.canteenpos.food.entity.UomConversion;

@Component
public class UomConversionMapper {

    public static UomConversion toEntity(UomConversionRequestDto dto, Uom fromUom, Uom toUom) {
        UomConversion conversion = new UomConversion();
        conversion.setFromUom(fromUom);
        conversion.setToUom(toUom);
        conversion.setConversionFactor(dto.getConversionFactor());
        conversion.setConversionMethod(dto.getConversionMethod());
        conversion.setEffectiveFrom(dto.getEffectiveFrom());
        conversion.setEffectiveTo(dto.getEffectiveTo());
        return conversion;
    }

    public static UomConversionResponseDto toDto(UomConversion entity) {
        return new UomConversionResponseDto(
                entity.getId(),
                entity.getFromUom().getId(),
                entity.getFromUom().getUomCode(),
                entity.getFromUom().getUomName(),
                entity.getToUom().getId(),
                entity.getToUom().getUomCode(),
                entity.getToUom().getUomName(),
                entity.getConversionFactor(),
                entity.getConversionMethod(),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo()
        );
    }
}

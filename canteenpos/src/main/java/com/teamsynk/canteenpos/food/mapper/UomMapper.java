package com.teamsynk.canteenpos.food.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.food.dto.request.UomRequestDto;
import com.teamsynk.canteenpos.food.dto.response.UomResponseDto;
import com.teamsynk.canteenpos.food.entity.Uom;

@Component
public class UomMapper {

    public static Uom toEntity(UomRequestDto dto) {
        Uom uom = new Uom();
        uom.setUomCode(dto.getUomCode());
        uom.setUomName(dto.getUomName());
        uom.setUomType(dto.getUomType());
        uom.setIsBase(dto.getIsBase());
        uom.setPrecisionValue(dto.getPrecisionValue());
        return uom;
    }

    public static UomResponseDto toDto(Uom entity) {
        return new UomResponseDto(
                entity.getId(),
                entity.getUomCode(),
                entity.getUomName(),
                entity.getUomType(),
                entity.getIsBase(),
                entity.getPrecisionValue()
        );
    }
}

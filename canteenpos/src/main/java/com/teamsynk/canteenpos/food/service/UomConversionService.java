package com.teamsynk.canteenpos.food.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.UomConversionRequestDto;
import com.teamsynk.canteenpos.food.dto.response.UomConversionResponseDto;
import com.teamsynk.canteenpos.food.entity.Uom;
import com.teamsynk.canteenpos.food.entity.UomConversion;
import com.teamsynk.canteenpos.food.mapper.UomConversionMapper;
import com.teamsynk.canteenpos.food.repository.UomConversionRepository;
import com.teamsynk.canteenpos.food.repository.UomRepository;

@Service
public class UomConversionService {

    private final UomConversionRepository conversionRepository;
    private final UomRepository uomRepository;

    public UomConversionService(UomConversionRepository conversionRepository, UomRepository uomRepository) {
        this.conversionRepository = conversionRepository;
        this.uomRepository = uomRepository;
    }

    @Transactional
    public UomConversionResponseDto createConversion(UomConversionRequestDto dto) {
        Uom fromUom = uomRepository.findById(dto.getFromUomId())
                .orElseThrow(() -> new ResourceNotFoundException("UOM", dto.getFromUomId()));
        Uom toUom = uomRepository.findById(dto.getToUomId())
                .orElseThrow(() -> new ResourceNotFoundException("UOM", dto.getToUomId()));

        conversionRepository.findActiveConversion(fromUom.getId(), toUom.getId(), LocalDate.now())
                .ifPresent(c -> { throw new IllegalArgumentException("Active UOM conversion already exists"); });

        UomConversion entity = UomConversionMapper.toEntity(dto, fromUom, toUom);
        return UomConversionMapper.toDto(conversionRepository.save(entity));
    }

    public UomConversionResponseDto getConversionById(UUID id) {
        UomConversion entity = conversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UOM Conversion", id));
        return UomConversionMapper.toDto(entity);
    }

    public List<UomConversionResponseDto> getAllConversions() {
        return conversionRepository.findAll().stream()
                .map(UomConversionMapper::toDto)
                .collect(Collectors.toList());
    }

    public UomConversionResponseDto getActiveConversion(UUID fromUomId, UUID toUomId) {
        return conversionRepository.findActiveConversion(fromUomId, toUomId, LocalDate.now())
                .map(UomConversionMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Active UOM Conversion not found for fromUom=" + fromUomId + ", toUom=" + toUomId, null));
    }

    @Transactional
    public UomConversionResponseDto updateConversion(UUID id, UomConversionRequestDto dto) {
        UomConversion existing = conversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UOM Conversion", id));

        Uom fromUom = uomRepository.findById(dto.getFromUomId())
                .orElseThrow(() -> new ResourceNotFoundException("UOM", dto.getFromUomId()));
        Uom toUom = uomRepository.findById(dto.getToUomId())
                .orElseThrow(() -> new ResourceNotFoundException("UOM", dto.getToUomId()));

        conversionRepository.findActiveConversion(fromUom.getId(), toUom.getId(), LocalDate.now())
                .ifPresent(c -> {
                    if (!c.getId().equals(id)) {
                        throw new IllegalArgumentException("Another active UOM conversion already exists");
                    }
                });

        existing.setFromUom(fromUom);
        existing.setToUom(toUom);
        existing.setConversionFactor(dto.getConversionFactor());
        existing.setConversionMethod(dto.getConversionMethod());
        existing.setEffectiveFrom(dto.getEffectiveFrom());
        existing.setEffectiveTo(dto.getEffectiveTo());

        return UomConversionMapper.toDto(conversionRepository.save(existing));
    }

    @Transactional
    public void deleteConversion(UUID id) {
        UomConversion existing = conversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UOM Conversion", id));
        existing.setIsActive(false);
        conversionRepository.save(existing);
    }
}

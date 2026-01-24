package com.teamsynk.canteenpos.food.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.UomRequestDto;
import com.teamsynk.canteenpos.food.dto.response.UomResponseDto;
import com.teamsynk.canteenpos.food.entity.Uom;
import com.teamsynk.canteenpos.food.mapper.UomMapper;
import com.teamsynk.canteenpos.food.repository.UomRepository;

@Service
public class UomService {

    private final UomRepository uomRepository;

    public UomService(UomRepository uomRepository) {
        this.uomRepository = uomRepository;
    }

    @Transactional
    public UomResponseDto createUom(UomRequestDto dto) {
        uomRepository.findByUomCodeAndIsActiveTrue(dto.getUomCode())
                .ifPresent(u -> { throw new IllegalArgumentException("UOM code already exists"); });

        if (dto.getIsBase() != null && dto.getIsBase()) {
            uomRepository.findByIsBaseTrueAndUomTypeAndIsActiveTrue(dto.getUomType())
                    .ifPresent(u -> { throw new IllegalArgumentException("Base UOM already exists for this type"); });
        }

        Uom entity = UomMapper.toEntity(dto);
        return UomMapper.toDto(uomRepository.save(entity));
    }

    public UomResponseDto getUomById(UUID id) {
        Uom entity = uomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UOM", id));
        return UomMapper.toDto(entity);
    }

    public List<UomResponseDto> getAllUoms() {
        return uomRepository.findAll().stream()
                .map(UomMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UomResponseDto> getAllActiveUoms() {
        return uomRepository.findAll().stream()
                .filter(Uom::getIsActive)
                .map(UomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UomResponseDto updateUom(UUID id, UomRequestDto dto) {
        Uom existing = uomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UOM", id));

        if (!existing.getUomCode().equals(dto.getUomCode())) {
            uomRepository.findByUomCodeAndIsActiveTrue(dto.getUomCode())
                    .ifPresent(u -> { throw new IllegalArgumentException("UOM code already exists"); });
        }

        if (dto.getIsBase() != null && dto.getIsBase()) {
            uomRepository.findByIsBaseTrueAndUomTypeAndIsActiveTrue(dto.getUomType())
                    .ifPresent(u -> {
                        if (!u.getId().equals(id)) {
                            throw new IllegalArgumentException("Base UOM already exists for this type");
                        }
                    });
        }

        existing.setUomCode(dto.getUomCode());
        existing.setUomName(dto.getUomName());
        existing.setUomType(dto.getUomType());
        existing.setIsBase(dto.getIsBase());
        existing.setPrecisionValue(dto.getPrecisionValue());

        return UomMapper.toDto(uomRepository.save(existing));
    }

    @Transactional
    public void deleteUom(UUID id) {
        Uom existing = uomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UOM", id));
        existing.setIsActive(false);
        uomRepository.save(existing);
    }
}

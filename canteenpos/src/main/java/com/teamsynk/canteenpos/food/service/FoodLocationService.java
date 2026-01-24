package com.teamsynk.canteenpos.food.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.FoodLocationRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodLocationResponseDto;
import com.teamsynk.canteenpos.food.entity.Food;
import com.teamsynk.canteenpos.food.entity.FoodLocation;
import com.teamsynk.canteenpos.food.mapper.FoodLocationMapper;
import com.teamsynk.canteenpos.food.repository.FoodLocationRepository;
import com.teamsynk.canteenpos.food.repository.FoodRepository;
import com.teamsynk.canteenpos.organization.repository.BranchRepository;

@Service
public class FoodLocationService {

    private final FoodLocationRepository locationRepository;
    private final FoodRepository foodRepository;
    private final BranchRepository branchRepository;

    public FoodLocationService(FoodLocationRepository locationRepository,
                               FoodRepository foodRepository,
                               BranchRepository branchRepository) {
        this.locationRepository = locationRepository;
        this.foodRepository = foodRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public FoodLocationResponseDto createLocation(FoodLocationRequestDto dto) {
        FoodLocation entity = resolveFoodLocation(dto);
        return FoodLocationMapper.toDto(locationRepository.save(entity));
    }

    @Transactional
    public List<FoodLocationResponseDto> bulkCreateOrUpdate(List<FoodLocationRequestDto> dtos) {

        List<FoodLocation> entities = dtos.stream()
                .map(this::resolveFoodLocation)
                .collect(Collectors.toList());

        return locationRepository.saveAll(entities)
                .stream()
                .map(FoodLocationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FoodLocationResponseDto updateLocation(
            UUID foodId,
            UUID locationId,
            Boolean isSellable,
            LocalDate effectiveFrom,
            LocalDate effectiveTo
    ) {

        FoodLocation existing = locationRepository
                .findByFood_IdAndLocation_IdAndIsActiveTrue(foodId, locationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "FoodLocation", foodId + " / " + locationId
                        ));

        if (isSellable != null) {
            existing.setIsSellable(isSellable);
        }
        if (effectiveFrom != null) {
            existing.setEffectiveFrom(effectiveFrom);
        }
        existing.setEffectiveTo(effectiveTo);

        return FoodLocationMapper.toDto(locationRepository.save(existing));
    }

    public FoodLocationResponseDto getLocationById(UUID id) {
        FoodLocation location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodLocation", id));
        return FoodLocationMapper.toDto(location);
    }

    public List<FoodLocationResponseDto> getAllLocationsByFood(UUID foodId) {
        return locationRepository.findAll()
                .stream()
                .filter(fl ->
                        fl.getFood().getId().equals(foodId)
                        && Boolean.TRUE.equals(fl.getIsActive()))
                .map(FoodLocationMapper::toDto)
                .collect(Collectors.toList());
    }

    public FoodLocationResponseDto getSellableFoodForToday(
            UUID foodId,
            UUID locationId
    ) {
        return locationRepository
                .findSellableFoodForDate(foodId, locationId, LocalDate.now())
                .map(FoodLocationMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sellable FoodLocation",
                                foodId + " / " + locationId
                        ));
    }

    @Transactional
    public void deleteLocation(UUID foodId, UUID locationId) {
        FoodLocation existing = locationRepository
                .findByFood_IdAndLocation_IdAndIsActiveTrue(foodId, locationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "FoodLocation", foodId + " / " + locationId
                        ));

        existing.setIsActive(false);
        existing.setIsSellable(false);
        locationRepository.save(existing);
    }

    private FoodLocation resolveFoodLocation(FoodLocationRequestDto dto) {

        Food food = foodRepository.findById(dto.getFoodId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food", dto.getFoodId()));

        var branch = branchRepository.findById(dto.getLocationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch", dto.getLocationId()));

        return locationRepository
                .findByFood_IdAndLocation_IdAndIsActiveTrue(
                        dto.getFoodId(),
                        dto.getLocationId()
                )
                .map(existing -> {
                    existing.setIsSellable(
                            dto.getIsSellable() != null ? dto.getIsSellable() : true
                    );
                    existing.setEffectiveFrom(dto.getEffectiveFrom());
                    existing.setEffectiveTo(dto.getEffectiveTo());
                    return existing;
                })
                .orElse(FoodLocationMapper.toEntity(dto, food, branch));
    }
}

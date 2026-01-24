package com.teamsynk.canteenpos.food.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.food.dto.request.FoodPriceRequestDto;
import com.teamsynk.canteenpos.food.dto.response.FoodPriceResponseDto;
import com.teamsynk.canteenpos.food.entity.Food;
import com.teamsynk.canteenpos.food.entity.FoodPrice;
import com.teamsynk.canteenpos.food.mapper.FoodPriceMapper;
import com.teamsynk.canteenpos.food.repository.FoodPriceRepository;
import com.teamsynk.canteenpos.food.repository.FoodRepository;
import com.teamsynk.canteenpos.organization.repository.BranchRepository;

@Service
public class FoodPriceService {

    private final FoodPriceRepository priceRepository;
    private final FoodRepository foodRepository;
    private final BranchRepository branchRepository;

    public FoodPriceService(
            FoodPriceRepository priceRepository,
            FoodRepository foodRepository,
            BranchRepository branchRepository
    ) {
        this.priceRepository = priceRepository;
        this.foodRepository = foodRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public FoodPriceResponseDto createPrice(FoodPriceRequestDto dto) {
        FoodPrice entity = resolveFoodPrice(dto);
        return FoodPriceMapper.toDto(priceRepository.save(entity));
    }

    @Transactional
    public List<FoodPriceResponseDto> bulkCreateOrUpdate(
            List<FoodPriceRequestDto> dtos
    ) {
        List<FoodPrice> entities = dtos.stream()
                .map(this::resolveFoodPrice)
                .collect(Collectors.toList());

        return priceRepository.saveAll(entities)
                .stream()
                .map(FoodPriceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FoodPriceResponseDto updatePrice(
            UUID priceId,
            FoodPriceRequestDto dto
    ) {
        FoodPrice existing = priceRepository.findById(priceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FoodPrice", priceId));

        validateEffectiveDates(
                dto.getEffectiveFrom(),
                dto.getEffectiveTo()
        );

        if (dto.getPrice() != null) {
            existing.setPrice(dto.getPrice());
        }
        if (dto.getCurrencyCode() != null) {
            existing.setCurrencyCode(dto.getCurrencyCode());
        }
        if (dto.getEffectiveFrom() != null) {
            existing.setEffectiveFrom(dto.getEffectiveFrom());
        }
        existing.setEffectiveTo(dto.getEffectiveTo());

        return FoodPriceMapper.toDto(
                priceRepository.save(existing)
        );
    }

    public FoodPriceResponseDto getPriceById(UUID id) {
        return priceRepository.findById(id)
                .map(FoodPriceMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FoodPrice", id));
    }

    public List<FoodPriceResponseDto> getAllPricesByFood(
            UUID foodId,
            UUID branchId
    ) {
        return priceRepository
                .findAllByFood_IdAndLocation_IdAndIsActiveTrue(
                        foodId,
                        branchId
                )
                .stream()
                .map(FoodPriceMapper::toDto)
                .collect(Collectors.toList());
    }

    public FoodPriceResponseDto getPriceForToday(
            UUID foodId,
            UUID branchId
    ) {
        return priceRepository
                .findPriceForDate(foodId, branchId, LocalDate.now())
                .map(FoodPriceMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "FoodPrice (today)",
                                foodId + " / " + branchId
                        ));
    }

    @Transactional
    public void deletePrice(UUID priceId) {
        FoodPrice existing = priceRepository.findById(priceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FoodPrice", priceId));

        existing.setIsActive(false);
        existing.setIsCurrent(false);
        priceRepository.save(existing);
    }

    private FoodPrice resolveFoodPrice(FoodPriceRequestDto dto) {

        validateEffectiveDates(
                dto.getEffectiveFrom(),
                dto.getEffectiveTo()
        );

        Food food = foodRepository.findById(dto.getFoodId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food", dto.getFoodId()));

        var branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch", dto.getBranchId()));

        priceRepository.findCurrentPrice(
                dto.getFoodId(),
                dto.getBranchId()
        ).ifPresent(existing -> {
            existing.setIsCurrent(false);
            existing.setEffectiveTo(
                    dto.getEffectiveFrom().minusDays(1)
            );
            priceRepository.save(existing);
        });

        return FoodPriceMapper.toEntity(dto, food, branch);
    }

    private void validateEffectiveDates(
            LocalDate from,
            LocalDate to
    ) {
        if (from != null && to != null && from.isAfter(to)) {
            throw new IllegalArgumentException(
                    "effectiveFrom must be before or equal to effectiveTo"
            );
        }
    }
}

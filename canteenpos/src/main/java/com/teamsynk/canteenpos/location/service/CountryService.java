package com.teamsynk.canteenpos.location.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.CountryRequestDto;
import com.teamsynk.canteenpos.location.dto.response.CountryResponseDto;
import com.teamsynk.canteenpos.location.entity.Country;
import com.teamsynk.canteenpos.location.mapper.CountryMapper;
import com.teamsynk.canteenpos.location.repository.CountryRepository;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    public CountryResponseDto createCountry(CountryRequestDto dto) {
        Country country = CountryMapper.toEntity(dto);
        country.setIsActive(true);
        return CountryMapper.toDto(countryRepository.save(country));
    }

    public CountryResponseDto getCountryById(UUID id) {
        Country country = countryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Country", id));
        return CountryMapper.toDto(country);
    }

    @Transactional
    public CountryResponseDto updateCountry(UUID id, CountryRequestDto dto) {
        Country existing = countryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Country", id));

        existing.setCountryName(dto.getCountryName());
        existing.setCountryCode(dto.getCountryCode());
        existing.setNationality(dto.getNationality());

        return CountryMapper.toDto(countryRepository.save(existing));
    }

    public List<CountryResponseDto> getAllActiveCountries() {
        return countryRepository.findByIsActiveTrue()
                .stream()
                .map(CountryMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<CountryResponseDto> getAllStates() {
    	return countryRepository.findAll()
    			.stream()
    			.map(CountryMapper::toDto)
    			.collect(Collectors.toList());
    }

    @Transactional
    public void deleteCountry(UUID id) {
        Country existing = countryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Country", id));
        existing.setIsActive(false);
        countryRepository.save(existing);
    }
}

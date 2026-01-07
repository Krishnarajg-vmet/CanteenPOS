package com.teamsynk.canteenpos.location.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.StateRequestDto;
import com.teamsynk.canteenpos.location.dto.response.StateResponseDto;
import com.teamsynk.canteenpos.location.entity.Country;
import com.teamsynk.canteenpos.location.entity.State;
import com.teamsynk.canteenpos.location.mapper.StateMapper;
import com.teamsynk.canteenpos.location.repository.CountryRepository;
import com.teamsynk.canteenpos.location.repository.StateRepository;

@Service
public class StateService {
	
	private final StateRepository stateRepository;
	private final CountryRepository countryRepository;
	
	StateService(StateRepository stateRepository, CountryRepository countryRepository) {
		this.stateRepository = stateRepository;
		this.countryRepository = countryRepository;
	}
	
	@Transactional
	public StateResponseDto createState(StateRequestDto dto) {
		Country country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Country", dto.getCountryId())
                );
		State state = StateMapper.toEntity(dto, country);
		state.setIsActive(true);
		return StateMapper.toDto(stateRepository.save(state));
	}
	
	public StateResponseDto getStateById(UUID id) {
		State state = stateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("State", id));
		return StateMapper.toDto(state);
	}
	
	@Transactional
	public StateResponseDto updateState(UUID id, StateRequestDto dto) {

	    State existing = stateRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("State", id));

	    existing.setStateName(dto.getStateName());

	    if (dto.getCountryId() != null &&
	        !dto.getCountryId().equals(existing.getCountry().getId())) {

	        Country country = countryRepository.findById(dto.getCountryId())
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Country", dto.getCountryId())
	                );

	        existing.setCountry(country);
	    }
	    
	    return StateMapper.toDto(stateRepository.save(existing));
	}
	
	public List<StateResponseDto> getAllActiveStates() {
		return stateRepository.findByIsActiveTrue()
				.stream()
				.map(StateMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<StateResponseDto> getAllStates() {
		return stateRepository.findAll()
				.stream()
				.map(StateMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteStateById(UUID id) {
		State existing = stateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("State", id));
		existing.setIsActive(false);
		stateRepository.save(existing);
	}

}

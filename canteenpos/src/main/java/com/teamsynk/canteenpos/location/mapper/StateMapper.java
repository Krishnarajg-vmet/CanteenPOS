package com.teamsynk.canteenpos.location.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.location.dto.request.StateRequestDto;
import com.teamsynk.canteenpos.location.dto.response.StateResponseDto;
import com.teamsynk.canteenpos.location.entity.Country;
import com.teamsynk.canteenpos.location.entity.State;

@Component
public class StateMapper {
	
	public static State toEntity(StateRequestDto dto, Country country) {
		if(dto==null) {
			throw new ResourceNotFoundException("State", "DTO is NULL");
		}
		
		if(country == null) {
			throw new ResourceNotFoundException("Country", "Entity with this ID is NULL");
		}
		
		State state = new State();
		state.setStateName(dto.getStateName());
		state.setCountry(country);
		
		return state;
	}
	
	public static StateResponseDto toDto(State state) {
		if(state == null) {
			throw new ResourceNotFoundException("State", "Entity with this ID is NULL");
		}
		
		UUID countryId = state.getCountry() != null ? state.getCountry().getId() : null;
        String countryName = state.getCountry() != null ? state.getCountry().getCountryName() : null;

		return new StateResponseDto(
				state.getId(),
				state.getStateName(),
				countryId,
				countryName,
				state.getCreatedDt(),
				state.getModifiedDt(),
				state.getCreatedBy(),
				state.getModifiedBy()
				);
	}

}

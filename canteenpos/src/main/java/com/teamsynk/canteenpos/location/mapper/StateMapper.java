package com.teamsynk.canteenpos.location.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.location.dto.request.StateRequestDto;
import com.teamsynk.canteenpos.location.dto.response.StateResponseDto;
import com.teamsynk.canteenpos.location.entity.Country;
import com.teamsynk.canteenpos.location.entity.State;

@Component
public class StateMapper {
	
	public static State toEntity(StateRequestDto dto, Country country) {		
		State state = new State();
		state.setStateName(dto.getStateName());
		state.setCountry(country);
		
		return state;
	}
	
	public static StateResponseDto toDto(State state) {	
		
		return new StateResponseDto(
				state.getId(),
				state.getStateName(),
				state.getCountry().getId(),
				state.getCountry().getCountryName(),
				state.getIsActive(),
				state.getCreatedDt(),
				state.getModifiedDt(),
				state.getCreatedBy(),
				state.getModifiedBy()
				);
	}

}

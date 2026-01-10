package com.teamsynk.canteenpos.user.mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.organization.dto.response.BranchResponseDto;
import com.teamsynk.canteenpos.organization.dto.response.DepartmentResponseDto;
import com.teamsynk.canteenpos.organization.entity.Employee;
import com.teamsynk.canteenpos.user.dto.request.UserRequestDto;
import com.teamsynk.canteenpos.user.dto.response.RoleResponseDto;
import com.teamsynk.canteenpos.user.dto.response.UserResponseDto;
import com.teamsynk.canteenpos.user.entity.User;

@Component
public class UserMapper {

    public static User toEntity(UserRequestDto dto, Employee employee) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setPasswordResetRequired(
                dto.getPasswordResetRequired() != null
                        ? dto.getPasswordResetRequired()
                        : Boolean.TRUE
        );
        user.setEmployee(employee);

        return user;
    }

    public static UserResponseDto toDto(
    		User user,
            Set<RoleResponseDto> roles,
            Set<BranchResponseDto> branches,
            Set<DepartmentResponseDto> departments) {

    	String employeeName = user.getEmployee().getFirstName() + " " + user.getEmployee().getLastName();
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getPasswordResetRequired(),
                user.getEmployee().getId(),
                employeeName,
                user.getEmployee().getEmployeeCode(),
                roles,
                branches,
                departments
        );
    }
}

package com.teamsynk.canteenpos.common.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.common.dto.EnumDto;
import com.teamsynk.canteenpos.common.enums.*;

@RestController
@RequestMapping("/api/v1/enums")
public class EnumRestController {

    @GetMapping("/titles")
    public ResponseEntity<List<EnumDto>> getTitles() {
        List<EnumDto> titles = Arrays.stream(Title.values())
                .map(t -> new EnumDto(t.name(), t.getLabel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(titles);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<EnumDto>> getGenders() {
        List<EnumDto> genders = Arrays.stream(Gender.values())
                .map(g -> new EnumDto(g.name(), g.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(genders);
    }

    @GetMapping("/marital-statuses")
    public ResponseEntity<List<EnumDto>> getMaritalStatuses() {
        List<EnumDto> statuses = Arrays.stream(MaritalStatus.values())
                .map(m -> new EnumDto(m.name(), m.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/blood-groups")
    public ResponseEntity<List<EnumDto>> getBloodGroups() {
        List<EnumDto> bloodGroups = Arrays.stream(BloodGroup.values())
                .map(b -> new EnumDto(b.name(), b.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bloodGroups);
    }

    @GetMapping("/employee-types")
    public ResponseEntity<List<EnumDto>> getEmployeeTypes() {
        List<EnumDto> types = Arrays.stream(EmployeeType.values())
                .map(e -> new EnumDto(e.name(), e.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(types);
    }

    @GetMapping("/employee-categories")
    public ResponseEntity<List<EnumDto>> getEmployeeCategories() {
        List<EnumDto> categories = Arrays.stream(EmployeeCategory.values())
                .map(c -> new EnumDto(c.name(), c.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }
}

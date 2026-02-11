package com.teamsynk.canteenpos.sales.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.sales.dto.response.FinancialSnapshotResponseDto;
import com.teamsynk.canteenpos.sales.mapper.FinancialSnapshotMapper;
import com.teamsynk.canteenpos.sales.service.FinancialSnapshotService;

@RestController
@RequestMapping("/api/orders/{orderId}/financial-snapshots")
public class FinancialSnapshotController {

    private final FinancialSnapshotService snapshotService;
    private final FinancialSnapshotMapper mapper;

    public FinancialSnapshotController(
            FinancialSnapshotService snapshotService,
            FinancialSnapshotMapper mapper
    ) {
        this.snapshotService = snapshotService;
        this.mapper = mapper;
    }

    @GetMapping("/latest")
    public FinancialSnapshotResponseDto getLatest(@PathVariable UUID orderId) {
        return mapper.toDto(snapshotService.getLatestSnapshot(orderId));
    }

    @GetMapping
    public List<FinancialSnapshotResponseDto> getAll(@PathVariable UUID orderId) {
        return snapshotService.findAll(orderId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

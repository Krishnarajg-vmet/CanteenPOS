package com.teamsynk.canteenpos.sales.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.sales.dto.response.FinancialSnapshotResponseDto;
import com.teamsynk.canteenpos.sales.entity.FinancialSnapshot;

@Component
public class FinancialSnapshotMapper {

    public FinancialSnapshotResponseDto toDto(FinancialSnapshot snapshot) {
        return new FinancialSnapshotResponseDto(
                snapshot.getId(),
                snapshot.getOrder().getId(),
                snapshot.getGrossAmount(),
                snapshot.getActiveItemAmount(),
                snapshot.getTotalPaid(),
                snapshot.getTotalRefunded(),
                snapshot.getNetPayable(),
                snapshot.getCapturedAt()
        );
    }
}

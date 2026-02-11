package com.teamsynk.canteenpos.payment.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.payment.dto.response.RefundResponseDto;
import com.teamsynk.canteenpos.payment.entity.Refund;

@Component
public class RefundMapper {

    public static RefundResponseDto toDto(Refund entity) {
        return new RefundResponseDto(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getPayment() != null ? entity.getPayment().getId() : null,
                entity.getRefundAmount(),
                entity.getRefundMode().name(),
                entity.getRefundReason()
        );
    }
}

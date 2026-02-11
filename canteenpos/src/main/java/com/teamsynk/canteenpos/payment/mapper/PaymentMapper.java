package com.teamsynk.canteenpos.payment.mapper;

import org.springframework.stereotype.Component;

import com.teamsynk.canteenpos.payment.dto.response.PaymentResponseDto;
import com.teamsynk.canteenpos.payment.entity.Payment;

@Component
public class PaymentMapper {

    public static PaymentResponseDto toDto(Payment entity) {
        return new PaymentResponseDto(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getCompanyId(),
                entity.getBranchId(),
                entity.getPaymentMode(),
                entity.getPaymentStatus(),
                entity.getAmount(),
                entity.getTransactionRef(),
                entity.getPaymentTime()
        );
    }
}

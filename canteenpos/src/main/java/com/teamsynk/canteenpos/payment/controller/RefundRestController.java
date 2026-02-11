package com.teamsynk.canteenpos.payment.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.payment.dto.response.RefundResponseDto;
import com.teamsynk.canteenpos.payment.entity.Refund;
import com.teamsynk.canteenpos.payment.mapper.RefundMapper;
import com.teamsynk.canteenpos.payment.service.RefundService;

@RestController
@RequestMapping("/api/v1/refunds")
public class RefundRestController {

    private final RefundService refundService;

    public RefundRestController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<RefundResponseDto> refundOrder(
            @PathVariable UUID orderId,
            @RequestParam String reason
    ) {
        Refund refund = refundService.processRefundForOrder(orderId, reason);
        return ResponseEntity.ok(RefundMapper.toDto(refund));
    }

    @PostMapping("/orders/{orderId}/payments/{paymentId}")
    public ResponseEntity<RefundResponseDto> refundPayment(
            @PathVariable UUID orderId,
            @PathVariable UUID paymentId,
            @RequestParam String reason
    ) {
        Refund refund =
                refundService.processRefundForPayment(orderId, paymentId, reason);

        return ResponseEntity.ok(RefundMapper.toDto(refund));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<RefundResponseDto>> getRefundsForOrder(
            @PathVariable UUID orderId
    ) {
        List<RefundResponseDto> refunds =
                refundService.getRefundsForOrder(orderId)
                        .stream()
                        .map(RefundMapper::toDto)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/orders/{orderId}/total")
    public ResponseEntity<?> getTotalRefundedAmount(
            @PathVariable UUID orderId
    ) {
        return ResponseEntity.ok(
                refundService.getTotalRefundedAmount(orderId)
        );
    }
}

package com.teamsynk.canteenpos.payment.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.payment.dto.request.PaymentRequestDto;
import com.teamsynk.canteenpos.payment.dto.response.PaymentResponseDto;
import com.teamsynk.canteenpos.payment.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> initiatePayment(
            @RequestBody PaymentRequestDto request
    ) {
        PaymentResponseDto response =
                paymentService.initiatePayment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{paymentId}/complete")
    public ResponseEntity<PaymentResponseDto> completePayment(
            @PathVariable UUID paymentId
    ) {
        PaymentResponseDto response =
                paymentService.completePayment(paymentId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{paymentId}/fail")
    public ResponseEntity<PaymentResponseDto> failPayment(
            @PathVariable UUID paymentId
    ) {
        PaymentResponseDto response =
                paymentService.failPayment(paymentId);

        return ResponseEntity.ok(response);
    }
}

package com.teamsynk.canteenpos.payment.service;

import java.util.UUID;

import com.teamsynk.canteenpos.payment.dto.request.PaymentRequestDto;
import com.teamsynk.canteenpos.payment.dto.response.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto initiatePayment(PaymentRequestDto request);

    PaymentResponseDto completePayment(UUID paymentId);

    PaymentResponseDto failPayment(UUID paymentId);

}

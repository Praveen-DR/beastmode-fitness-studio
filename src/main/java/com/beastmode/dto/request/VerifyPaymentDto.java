package com.beastmode.dto.request;

import com.beastmode.models.PaymentStatus;

public record VerifyPaymentDto(String paymentId, PaymentStatus paymentStatus) {
}

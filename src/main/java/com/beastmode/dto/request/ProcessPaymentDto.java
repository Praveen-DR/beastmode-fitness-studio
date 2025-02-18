package com.beastmode.dto.request;

import com.beastmode.models.PaymentMethod;
import com.beastmode.models.PaymentStatus;

import java.time.LocalDateTime;

public record ProcessPaymentDto(String userId, String membershipId, Double amount, LocalDateTime paymentDate, PaymentStatus paymentStatus, PaymentMethod paymentMethod, String transactionId) {
}

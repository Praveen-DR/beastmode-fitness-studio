package com.beastmode.mappers;

import com.beastmode.models.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentMapper {
    public Payment toPayment(String paymentId, User user, Membership membership, Double amount, LocalDateTime paymentDate, PaymentStatus paymentStatus, PaymentMethod paymentMethod, String transactionId) {
        return Payment.builder()
                .paymentId(paymentId)
                .user(user)
                .membership(membership)
                .amount(amount)
                .paymentDate(paymentDate)
                .paymentStatus(paymentStatus)
                .paymentMethod(paymentMethod)
                .transactionId(transactionId)
                .build();
    }

}

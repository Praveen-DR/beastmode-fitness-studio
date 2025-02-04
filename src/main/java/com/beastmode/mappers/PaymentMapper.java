package com.beastmode.mappers;

import com.beastmode.models.Membership;
import com.beastmode.models.Payment;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(String paymentId, User userId, double amount, String paymentDate) {
        return Payment.builder()
                .paymentId(paymentId)
                .user(userId)
                .amount(amount)
                .paymentDate(paymentDate)
                .build();
    }

}

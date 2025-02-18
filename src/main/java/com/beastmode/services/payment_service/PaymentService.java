package com.beastmode.services.payment_service;

import com.beastmode.models.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PaymentService {
    String processPayment(String userId, String membershipId, Double amount, LocalDateTime paymentDate, PaymentStatus paymentStatus, PaymentMethod paymentMethod, String transactionId);

    List<Payment> getPaymentsByUserId(String userId);

    Payment getPaymentById(String paymentId);

    List<Payment> getAllPayments();

    String verifyPayment(String paymentId, PaymentStatus paymentStatus);

    String deletePayment(String paymentId);






}

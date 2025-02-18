package com.beastmode.controllers;

import com.beastmode.dto.request.ProcessPaymentDto;
import com.beastmode.dto.request.VerifyPaymentDto;
import com.beastmode.models.Payment;
import com.beastmode.services.payment_service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/v1/getPaymentByUserId")
    ResponseEntity<List<Payment>> getUserPayments(@RequestParam("userId") String userId){
        return ResponseEntity.status(200).body(paymentService.getPaymentsByUserId(userId));
    }

    @PostMapping("/v1/processPayment")
    ResponseEntity<String> processPayment(@RequestBody ProcessPaymentDto data) {
        String message = paymentService.processPayment(
                data.userId(), data.membershipId(), data.amount(), data.paymentDate(),
                data.paymentStatus(), data.paymentMethod(), data.transactionId()
        );
        return ResponseEntity.status(201).body(message);
    }


    @GetMapping("/v1/getPaymentById")
    ResponseEntity<Payment> getPaymentById(@RequestParam("paymentId") String paymentId) {
        return ResponseEntity.status(200).body(paymentService.getPaymentById(paymentId));
    }

    @GetMapping("/v1/getAllPayments")
    ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.status(200).body(paymentService.getAllPayments());
    }

    @PostMapping("/v1/verifyPayment")
    ResponseEntity<String> verifyPayment(@RequestBody VerifyPaymentDto data) {
        String message = paymentService.verifyPayment(data.paymentId(), data.paymentStatus());
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/v1/deletePayment")
    ResponseEntity<String> deletePayment(@RequestParam("paymentId") String paymentId) {
        String message = paymentService.deletePayment(paymentId);
        return ResponseEntity.status(200).body(message);
    }
}

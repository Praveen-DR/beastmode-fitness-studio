package com.beastmode.services.payment_service;

import com.beastmode.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    
}

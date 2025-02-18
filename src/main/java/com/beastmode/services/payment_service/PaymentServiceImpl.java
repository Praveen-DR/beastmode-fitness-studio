package com.beastmode.services.payment_service;

import com.beastmode.exceptions.ApiRequestException;
import com.beastmode.mappers.PaymentMapper;
import com.beastmode.models.*;
import com.beastmode.repositories.PaymentRepository;
import com.beastmode.services.membership_service.MembershipService;
import com.beastmode.services.user_service.UserService;
import com.beastmode.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UUIDUtil uuidUtil;
    private final UserService userService;
    private final MembershipService membershipService;

    PaymentServiceImpl(PaymentRepository paymentRepository, UserService userService, MembershipService membershipService, PaymentMapper paymentMapper, UUIDUtil uuidUtil){
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.uuidUtil = uuidUtil;
        this.userService = userService;
        this.membershipService = membershipService;
    }

    @Override
    public String processPayment(String userId, String membershipId, Double amount, LocalDateTime paymentDate,PaymentStatus paymentStatus,  PaymentMethod paymentMethod, String transactionId) {
        if (paymentRepository.existsByTransactionId(transactionId)) {
            throw new ApiRequestException("Transaction ID already exists");
        }
        User user = userService.getUserById(userId);
        Membership membership = membershipService.getMembershipById(membershipId);
        Payment payment = paymentMapper.toPayment(uuidUtil.generateUuid(), user, membership, amount, paymentDate, paymentStatus, paymentMethod, transactionId);
        paymentRepository.save(payment);
        return "Payment Processed Successfully";
    }

    @Override
    public List<Payment> getPaymentsByUserId(String userId) {
        List<Payment> payment = paymentRepository.findAllByUser_UserId(userId);
        if (payment.isEmpty()) {
            throw new ApiRequestException("No payments found for this User ID");
        }
        return payment;
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(()-> new ApiRequestException("Payment ID Does not exists"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public String verifyPayment(String paymentId, PaymentStatus paymentStatus) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ApiRequestException("Payment ID does not exist"));

        if (payment.getPaymentStatus() == paymentStatus) {
            throw new ApiRequestException("Payment is already verified with this status");
        }
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
        return "Payment Verified and Updated Successfully";
    }

    @Override
    public String deletePayment(String paymentId) {
        if (!paymentRepository.existsById(paymentId)){
            throw new ApiRequestException("Payment ID not found");
        }
        paymentRepository.deleteById(paymentId);
        return "Payment deleted Successfully";
    }
}

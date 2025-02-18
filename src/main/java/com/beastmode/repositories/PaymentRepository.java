package com.beastmode.repositories;

import com.beastmode.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    boolean existsByTransactionId(String transactionId);
    List<Payment> findAllByUser_UserId(String userId);

}

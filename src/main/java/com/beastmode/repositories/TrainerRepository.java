package com.beastmode.repositories;

import com.beastmode.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {
    boolean existsByPhoneNumber(String phoneNumber);
}

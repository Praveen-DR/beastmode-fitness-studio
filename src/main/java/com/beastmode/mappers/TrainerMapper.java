package com.beastmode.mappers;

import com.beastmode.models.Trainer;
import org.springframework.stereotype.Service;

@Service
public class TrainerMapper {
    public Trainer toTrainer(String trainerId, String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive) {
        return Trainer.builder()
                .trainerId(trainerId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .specialization(specialization)
                .experience(experience)
                .isActive(isActive)
                .build();
    }
}

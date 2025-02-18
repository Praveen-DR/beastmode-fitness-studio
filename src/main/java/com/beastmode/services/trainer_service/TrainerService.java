package com.beastmode.services.trainer_service;

import com.beastmode.models.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainerService {
    String createTrainer(String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive );

    String updateTrainer(String trainerId ,String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive);

    String deleteTrainer(String trainerId);

    List<Trainer> getAllTrainer();

    Trainer getTrainerById(String trainerId);
}

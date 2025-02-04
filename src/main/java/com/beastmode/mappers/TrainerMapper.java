package com.beastmode.mappers;

import com.beastmode.models.Trainer;
import org.springframework.stereotype.Service;

@Service
public class TrainerMapper {
    public Trainer toTrainer(String trainerId, String name) {
        return Trainer.builder()
                .trainerId(trainerId)
                .name(name)
                .build();
    }
}

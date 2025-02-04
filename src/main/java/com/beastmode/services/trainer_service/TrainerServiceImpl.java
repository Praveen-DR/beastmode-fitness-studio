package com.beastmode.services.trainer_service;

import com.beastmode.repositories.TrainerRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService{
    private final TrainerRepository trainerRepository;

    TrainerServiceImpl(TrainerRepository trainerRepository){
        this.trainerRepository = trainerRepository;
    }



}
